package com.work.theIsle.hilt.httpProcessor

import com.google.gson.Gson
import com.work.supportlib.ReflectUtils

/**
 * @Author TIKOU
 * @Date 2022/7/13-18:34
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
abstract class HttpCallback<R> : ICallback {
    override fun onSuccess(result: String) {
        val clz = ReflectUtils.analysisClassInfo(this)
        val gson = Gson()
        val result: R = gson.fromJson(result, clz) as R
        onSuccessResult(result)
    }

    abstract fun onSuccessResult(result: Any?)

    override fun onFail(e: String) {

    }
}