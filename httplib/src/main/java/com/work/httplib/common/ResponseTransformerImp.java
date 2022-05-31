package com.work.httplib.common;

import static com.work.httplib.common.ExceptionEngine.handleException;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.work.supportlib.ReflectUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author Administrator
 * @Date 2022/5/31-14:05
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
public class ResponseTransformerImp<T> implements ObservableTransformer<IResponse<T>, T>, LifecycleObserver {
    final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public @NonNull ObservableSource<T> apply(@NonNull Observable<IResponse<T>> upstream) {
        return upstream.doOnSubscribe(compositeDisposable::add).onErrorResumeNext(throwable -> {
            // 代码报错处理
            return Observable.error(handleException(throwable));
        }).flatMap(tiResponse -> {
            if (tiResponse.getCode().equals("0")) {
                if (tiResponse.getData() != null) {
                    return Observable.just(tiResponse.getData());
                } else {
                    Class<?> clazz = ReflectUtils.INSTANCE.analysisClassInfo(tiResponse);
                    T obj = (T) clazz.newInstance();
                    return Observable.just(obj);
                }
            }
            return Observable.error(new ServerException(tiResponse.getCode(), tiResponse.getMsg()));
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ResponseTransformerImp<T> get(LifecycleOwner lifecycleOwner) {
        ResponseTransformerImp<T> transformerImp = new ResponseTransformerImp<>();
        lifecycleOwner.getLifecycle().addObserver(transformerImp);
        return transformerImp;
    }
}
