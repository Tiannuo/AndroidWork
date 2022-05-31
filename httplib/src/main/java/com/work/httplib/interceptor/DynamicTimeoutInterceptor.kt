package com.work.httplib.interceptor

import com.work.httplib.config.TimeOutConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * @author zhaojian
 * @time 2019/8/26 9:24
 * @describe
 */
class DynamicTimeoutInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest: Request = chain.request()
        val method = parseMethod(oldRequest)
        if (TimeOutConfig.readTimeOutMap.containsKey(method)) {
            val timeout = TimeOutConfig.readTimeOutMap[method]!!
            chain.withReadTimeout(timeout, TimeUnit.SECONDS)
        }
        if (TimeOutConfig.writeTimeOutMap.containsKey(method)) {
            val timeout = TimeOutConfig.writeTimeOutMap[method]!!
            chain.withWriteTimeout(timeout, TimeUnit.SECONDS)
        }
        if (TimeOutConfig.connectTimeOutMap.containsKey(method)) {
            val timeout = TimeOutConfig.connectTimeOutMap[method]!!
            chain.withConnectTimeout(timeout, TimeUnit.SECONDS)
        }
        return chain.proceed(oldRequest)
    }

    private fun parseMethod(request: Request): String {
        val url = request.url.toString()
        val index = url.lastIndexOf("/")
        return if (index > 0) {
            url.substring(index + 1, url.length)
        } else {
            ""
        }
    }
}