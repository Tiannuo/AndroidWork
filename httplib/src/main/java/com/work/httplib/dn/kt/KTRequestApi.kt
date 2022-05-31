package com.work.httplib.dn.kt

import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.core.Observable

/**

 * @Author Administrator
 * @Date 2022/5/31-18:04
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
object KTRequestApi {
    fun <T> request(
        owner: LifecycleOwner,
        obs: Observable<KTBaseResponse<T>>,
        callback: KTICallback<T>
    ) {
        obs.compose(KTResponseTransformer.obtain(owner))
            .subscribe({
                callback.onSuccess(it)
            }
            ) {
                callback.onFailure(KTApiException.handleException(it))
            }
    }


}