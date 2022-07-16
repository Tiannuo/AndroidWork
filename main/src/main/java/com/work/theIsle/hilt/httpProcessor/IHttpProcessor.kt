package com.work.theIsle.hilt.httpProcessor

/**
 * @Author TIKOU
 * @Date 2022/7/13-18:31
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
interface IHttpProcessor {
    fun post(url: String, params: Map<String, Any>, callback: ICallback?)
}