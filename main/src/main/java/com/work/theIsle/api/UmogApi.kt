package com.work.theIsle.api

import com.work.login.bean.QingHuaBean
import retrofit2.http.GET

/**
 * @Author TIKOU
 * @Date 2022/7/6-15:54
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description umog的接口api
 */
interface UmogApi {
    @GET("rand.qinghua")
    suspend fun loadQing(): QingHuaBean
}