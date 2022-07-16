package com.work.theIsle.hilt.httpProcessor

import com.work.supportlib.LoggerUtils
import javax.inject.Inject

/**
 * @Author TIKOU
 * @Date 2022/7/14-20:30
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class OkhttpProcessor @Inject constructor():IHttpProcessor {
    override fun post(url: String, params: Map<String, Any>, callback: ICallback?) {
        LoggerUtils.i("OkhttpProcessor")
    }
}