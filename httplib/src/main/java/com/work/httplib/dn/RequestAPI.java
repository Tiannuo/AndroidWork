package com.work.httplib.dn;

import androidx.lifecycle.LifecycleOwner;

import com.work.httplib.dn.kt.KTApiException;
import com.work.httplib.dn.kt.KTICallback;
import com.work.httplib.dn.kt.KTBaseResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;


public class RequestAPI {

    public static <T> void request(Observable<? extends KTBaseResponse<T>> o,
                                   LifecycleOwner lifecycleOwner,
                                   KTICallback<T> callback) {
        Disposable d = o.compose(ResponseTransformer.obtain(lifecycleOwner))
                .subscribe(t -> callback.onSuccess(t), throwable -> callback.onFailure(KTApiException.handleException(throwable)));
    }


}
