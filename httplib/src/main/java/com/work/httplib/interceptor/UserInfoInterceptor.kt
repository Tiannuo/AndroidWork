package com.work.httplib.interceptor

import android.os.Build
import android.text.TextUtils
import com.work.httplib.params.CommonParams
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URLEncoder
import java.util.concurrent.ConcurrentHashMap

/**
 * 设置请求通用的参数拦截器
 * Created by zhaojian on 2018/2/1.
 */
internal class UserInfoInterceptor private constructor() : Interceptor {
    private var deviceID: String? = null
    private var appVersion: String? = null
    private var xInstance: String? = null
    private var commonParams: CommonParams? = null
    private val additionalHeadMap: MutableMap<String, String> = ConcurrentHashMap()
    fun init(institutionId: String?, deviceID: String?, appVersion: String?) {
        xInstance = institutionId
        this.deviceID = deviceID
        this.appVersion = appVersion
    }

    /**
     * 初始化登陆成功后的公共入参
     */
    fun initCommonParams(commonParams: CommonParams?) {
        this.commonParams = commonParams
    }

    /**
     * 增加额外的头 content json
     * 例如 Gson().toJson(map)
     */
    fun setAdditionalHead(method: String, content: String) {
        additionalHeadMap[method] = content
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest: Request = chain.request()
        val requestBuilder:Request.Builder = oldRequest.newBuilder()
        val method = parseMethod(oldRequest)
        if (!TextUtils.isEmpty(method) && additionalHeadMap.containsKey(method.trim { it <= ' ' })) {
            requestBuilder.addHeader(
                CommonParams.X_USERINFO,
                URLEncoder.encode(additionalHeadMap.remove(method.trim { it <= ' ' }), "utf-8")
            )
        }
        (if (commonParams == null) "" else commonParams!!.authorization)?.let {
            requestBuilder.addHeader(
                CommonParams.AUTHORIZATION,
                it
            )
        }
        xInstance?.let { requestBuilder.addHeader(CommonParams.X_INSTANCE, it) }
        (if (commonParams == null) "" else commonParams!!.longitude)?.let {
            requestBuilder.addHeader(
                CommonParams.X_LONGITUDE,
                it
            )
        }
        (if (commonParams == null) "" else commonParams!!.latitude)?.let {
            requestBuilder.addHeader(
                CommonParams.X_LATITUDE,
                it
            )
        }
        deviceID?.let { requestBuilder.addHeader(CommonParams.X_DEVICEID, it) }
        requestBuilder.addHeader(CommonParams.X_MOBILEBRAND, Build.BRAND)
        requestBuilder.addHeader(CommonParams.X_MOBILETYPE, Build.MODEL)
        requestBuilder.addHeader(
            CommonParams.X_MOBILESYSVERSION,
            Build.VERSION.SDK_INT.toString() + ""
        )
        appVersion?.let { requestBuilder.addHeader(CommonParams.X_APPVERSION, it) }
        requestBuilder.addHeader(CommonParams.X_PLATFORM, "android")
        val newRequest: Request = requestBuilder.build()
        return chain.proceed(newRequest)
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

    companion object {
        var instance: UserInfoInterceptor = UserInfoInterceptor()
    }
}