package com.work.httplib.common

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

/**
 * @Author Administrator
 * @Date 2022/5/31-14:17
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
object ExceptionEngine {
    //对应HTTP的状态码
    private const val UNAUTHORIZED = 401
    private const val FORBIDDEN = 403
    private const val NOT_FOUND = 404
    private const val REQUEST_TIMEOUT = 408
    private const val INTERNAL_SERVER_ERROR = 500
    private const val BAD_GATEWAY = 502
    private const val SERVICE_UNAVAILABLE = 503
    private const val GATEWAY_TIMEOUT = 504
    @JvmStatic
    fun handleException(e: Throwable?): ApiException1 {
        val ex: ApiException1
        return if (e is HttpException) {             //HTTP错误
            ex = ApiException1(e, Error.HTTP_ERROR)
            when (e.code()) {
                UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT, INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> ex.message =
                    "网络错误" //均视为网络错误
                else -> ex.message = "网络错误"
            }
            ex
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {
            ex = ApiException1(e, Error.PARSE_ERROR)
            ex.message = "解析错误" //均视为解析错误
            ex
        } else if (e is ConnectException) {
            ex = ApiException1(e, Error.NETWORD_ERROR)
            ex.message = "连接失败" //均视为网络错误
            ex
        } else {
            ex = ApiException1(e, Error.UNKNOWN)
            ex.message = "未知错误" //未知错误
            ex
        }
    }
}