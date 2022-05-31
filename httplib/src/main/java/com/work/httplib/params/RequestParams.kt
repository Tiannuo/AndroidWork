package com.work.httplib.params

/**
 * Created by ww on 2018/2/2.
 */
class RequestParams<T> {
    var method: String? = null
    var paramMap: Map<String, T>? = null
    var localMethodName: String? = null

    companion object {
        const val METHOD = "method"
        const val PARAMS = "params"
    }
}