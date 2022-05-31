package com.work.httplib.common;

import androidx.lifecycle.LifecycleOwner;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * @Author Administrator
 * @Date 2022/5/31-15:22
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
public class IRequestApi {
    public static <T> void request(Observable<? extends IResponse<T>> obs
            , LifecycleOwner owner
            , Callback<T> callback) {
        obs.compose(ResponseTransformerImp.get(owner))
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Throwable {
                        callback.onSuccess(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        //callback
                    }
                })
        ;
    }
}
