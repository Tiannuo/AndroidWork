package com.work.login.api

import com.work.login.bean.QingHuaBean
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("rand.qinghua")
    suspend fun loadQing(@Query("format") format: String): QingHuaBean
}