package com.work.theIsle.hilt.httpProcessor

/**
 * @Author TIKOU
 * @Date 2022/7/13-18:32
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
interface ICallback {
    fun onSuccess(result: String)
    fun onFail(e: String)
}