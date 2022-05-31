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
class KTApiException(var code: String? = "-1", var errorMsg: String? = "null") : Exception() {
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
        fun handleException(e: Throwable): KTApiException {
            if (e is KTApiException) {
                return e
            }
            val ex: KTApiException
            return if (e is JsonParseException
                || e is JSONException
                || e is ParseException
            ) {
                ex = KTApiException(PARSE_ERROR.toString(), "数据解析异常")
                ex
            } else if (e is ConnectException
                || e is UnknownHostException
                || e is SocketTimeoutException
            ) {
                ex = KTApiException(NETWORK_ERROR.toString(), "网络请求异常")
                ex
            } else {
                ex = KTApiException(UNKNOWN_ERROR.toString(), "其它异常：" + e.message)
                e.printStackTrace()
                ex
            }
        }
    }
}