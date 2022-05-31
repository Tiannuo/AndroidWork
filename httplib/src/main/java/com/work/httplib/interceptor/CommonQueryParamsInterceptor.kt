package com.work.httplib.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**

 * @Author Administrator
 * @Date 2022/5/31-16:18
 * @Email wangweitikou1994@gmail.com
 * @Des 增加公共参数
 */
class CommonQueryParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        var request: Request? = null
        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter("format", "json")
            .build()
        request = originalRequest.newBuilder().url(modifiedUrl).build()
        return chain.proceed(request)
    }
}