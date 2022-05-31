package com.work.httplib.handle

/**
 * Created by ww on 2018/2/5.
 */
class HttpErrorDetail {
    @JvmField
    var errorCode: String? = null
    @JvmField
    var errorMsg: String? = null

    constructor() {}
    constructor(errorCode: String?, errorMsg: String?) {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
    }

    override fun toString(): String {
        return "$errorCode|$errorMsg"
    }

    companion object {
        const val HTTP_ERROR_NO_CONNECT = "java.net.ConnectException: Failed to connect"
        const val HTTP_ERROR_HOST_FAILED = "Unable to resolve host"
        const val HTTP_ERROR_SERVER_CLOSE = "HTTP 502"
        const val HTTP_ERROR_410 = "HTTP 410 Gone"
        const val HTTP_ERROR_404 = "404"

        /*账号在其他设备登录*/
        const val HTTP_ERROR_401 = "401"
        const val HTTP_ERROR_GSON_JSON_SYNTAX_EXCEPTION = "com.google.gson.JsonSyntaxException"
        const val HTTP_ERROR_GSON_JSON_OTHER_EXCEPTION = "com.google.gson"
        const val HTTP_ERROR_BODY_NULL = "End of input at line 1 column 1"
        const val HTTP_ERROR_TIME_OUT = "java.net.SocketTimeoutException: failed to connect to "
        const val HTTP_ERROR_UNKNOWN = "HTTP_ERROR_UNKNOWN "
        const val SERVER_DATA_NULL = "server_data_null"
        const val SERVER_DATA_CODE_NOT_0 = "server_data_code_not_0"
    }
}