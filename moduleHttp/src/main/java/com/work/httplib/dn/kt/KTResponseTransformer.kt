package com.work.httplib.dn.kt

import android.text.TextUtils
import androidx.lifecycle.*
import com.work.httplib.common.ExceptionEngine
import com.work.httplib.dn.ResponseTransformer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers

/**

 * @Author Administrator
 * @Date 2022/5/31-18:03
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
class KTResponseTransformer<T : Any> : ObservableTransformer<KTBaseResponse<T>, T>,
    DefaultLifecycleObserver {

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun apply(upstream: Observable<KTBaseResponse<T>>): ObservableSource<T>? {
        return upstream.doOnSubscribe(compositeDisposable::add)
            .onErrorResumeNext {
                Observable.error(ExceptionEngine.handleException(it))
            }
            .flatMap(Function<KTBaseResponse<T>, ObservableSource<T>> { response ->
                // 对响应数据统一处理
                val content: T? = response.content
                if (TextUtils.equals("1", response.code) && content != null) {
                    return@Function Observable.just(content)
                }
                Observable.error(KTApiException(response.code, response.msg))
            })
            .subscribeOn(Schedulers.io())//指定事件产生的线程(请求的线程)
            .observeOn(AndroidSchedulers.mainThread());// 指定事件处理的线程 (响应的线程)
    }

    companion object {
        fun <T> obtain(lifecycleOwner: LifecycleOwner): ResponseTransformer<T> {
            val transformer: ResponseTransformer<T> = ResponseTransformer<T>()
            lifecycleOwner.lifecycle.addObserver(transformer)
            return transformer
        }
    }
}