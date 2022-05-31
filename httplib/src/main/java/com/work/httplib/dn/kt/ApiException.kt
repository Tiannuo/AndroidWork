package com.work.httplib.dn.kt

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * 通用请求异常封装类
 */
class ApiException(var code: String, var errorMsg: String) : Exception() {
    override fun toString(): String {
        return "ApiException{" +
                "code='" + code + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}'
    }

    companion object {
        private const val UNKNOWN_ERROR = -0x10
        private const val PARSE_ERROR = -0x11
        private const val NETWORK_ERROR = -0x12

        /**
         * Throwable --> ApiException
         *
         * @param e
         * @return
         */
        @JvmStatic
        fun handleException(e: Throwable): ApiException {
            if (e is ApiException) {
                return e
            }
            val ex: ApiException
            return if (e is JsonParseException
                || e is JSONException
                || e is ParseException
            ) {
                ex = ApiException(PARSE_ERROR.toString(), "数据解析异常")
                ex
            } else if (e is ConnectException
                || e is UnknownHostException
                || e is SocketTimeoutException
            ) {
                ex = ApiException(NETWORK_ERROR.toString(), "网络请求异常")
                ex
            } else {
                ex = ApiException(UNKNOWN_ERROR.toString(), "其它异常：" + e.message)
                e.printStackTrace()
                ex
            }
        }
    }
}