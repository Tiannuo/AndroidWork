package com.work.login.api

import com.work.httplib.dn.kt.BaseResponse
import com.work.httplib.dn.kt.IResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**

 * @Author Administrator
 * @Date 2022/5/31-20:15
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
interface UserObsApi {
    @GET("rand.qinghua")
    fun loadQing(): Observable<IResponse<String>>
}