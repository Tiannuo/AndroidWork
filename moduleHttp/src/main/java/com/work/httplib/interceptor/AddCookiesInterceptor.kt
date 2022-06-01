package com.work.httplib.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AddCookiesInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var builder: Request.Builder = chain.request().newBuilder();
        builder.addHeader("Cookie", "")
        return chain.proceed(builder.build())
    }
}