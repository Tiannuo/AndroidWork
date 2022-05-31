package com.work.httplib.dn;

import androidx.lifecycle.LifecycleOwner;

import com.work.httplib.dn.kt.ApiException;
import com.work.httplib.dn.kt.ICallback;
import com.work.httplib.dn.kt.IResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;


public class RequestAPI {

    public static <T> void request(Observable<? extends IResponse<T>> o,
                                   LifecycleOwner lifecycleOwner,
                                   ICallback<T> callback) {
        Disposable d = o.compose(ResponseTransformer.obtain(lifecycleOwner))
                .subscribe(t -> callback.onSuccess(t), throwable -> callback.onFailure(ApiException.handleException(throwable)));
    }


}
