package com.work.httplib.dn;

import android.text.TextUtils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.work.httplib.dn.kt.ApiException;
import com.work.httplib.dn.kt.IResponse;
import com.work.supportlib.ReflectUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Observable<IResponse<T>> -> Observable<T>
 * 实现：
 * 1. 对线程进行切换，达到代码复用的目标
 * 2. 对RxJava生命周期管理，防止内存泄漏
 * 3. 对响应数据统一处理，获取到真正想用的data，进行业务处理
 */
public class ResponseTransformer<T> implements ObservableTransformer<IResponse<T>, T>, LifecycleObserver {

    final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @NonNull
    @Override
    public ObservableSource<T> apply(@NonNull Observable<IResponse<T>> upstream) {
        return upstream.doOnSubscribe(compositeDisposable::add).onErrorResumeNext(throwable -> {
            // 出现异常统一处理 (非业务性的异常)
            return Observable.error(ApiException.handleException(throwable));
        }).flatMap((Function<IResponse<T>, ObservableSource<T>>) response -> {
            // 对响应数据统一处理
            if (TextUtils.equals("1",response.getCode())) {
                if (response.getContent()!= null) {
                    return Observable.just(response.getContent());
                } else {
                    // 业务请求可能成功了，但是data是NULL
                    // 通过反射手动创建data，这个data一般是没有实际用途
                    Class<?> clz = ReflectUtils.INSTANCE.analysisClassInfo(response);
                    T object = (T) clz.newInstance();
                    return Observable.just(object);
                }
            }
            return Observable.error(new ApiException(response.getCode(), response.getMsg()));
        }).subscribeOn(Schedulers.io())//指定事件产生的线程(请求的线程)
                .observeOn(AndroidSchedulers.mainThread());// 指定事件处理的线程 (响应的线程)
    }

    public static <T> ResponseTransformer<T> obtain(LifecycleOwner lifecycleOwner) {
        ResponseTransformer<T> transformer = new ResponseTransformer<>();
        lifecycleOwner.getLifecycle().addObserver(transformer);
        return transformer;
    }

}
