package com.work.httplib.dn.kt

import android.text.TextUtils
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.work.httplib.common.ExceptionEngine
import com.work.httplib.dn.ResponseTransformer
import com.work.supportlib.ReflectUtils
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
class KTResponseTransformer<T> : ObservableTransformer<IResponse<T>, T>, LifecycleObserver {

    private val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun apply(upstream: Observable<IResponse<T>>): ObservableSource<T>? {
        return upstream.doOnSubscribe(compositeDisposable::add)
            .onErrorResumeNext {
                Observable.error(ExceptionEngine.handleException(it))
            }
            .flatMap(Function<IResponse<T>, ObservableSource<T>?> { response ->
                // 对响应数据统一处理
                if (TextUtils.equals("1",response.code)) {
                    if (response.content != null) {
                        return@Function Observable.just(response.content)
                    } else {
                        // 业务请求可能成功了，但是data是NULL
                        // 通过反射手动创建data，这个data一般是没有实际用途
                        val clz: Class<*> = ReflectUtils.analysisClassInfo(response)
                        val `object` = clz.newInstance() as T
                        return@Function Observable.just(`object`)
                    }
                }
                Observable.error(ApiException(response.code!!, response.msg!!))
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