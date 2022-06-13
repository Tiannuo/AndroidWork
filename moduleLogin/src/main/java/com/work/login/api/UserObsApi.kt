package com.work.login.api

import com.work.httplib.dn.kt.KTBaseResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.POST

/**

 * @Author Administrator
 * @Date 2022/5/31-20:15
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
interface UserObsApi {
    @POST("rand.qinghua")
    fun loadQing(): Observable<KTBaseResponse<String>>
}