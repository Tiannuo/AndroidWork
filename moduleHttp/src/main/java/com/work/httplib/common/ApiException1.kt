package com.work.httplib.common

import java.lang.Exception

/**
 * @Author Administrator
 * @Date 2022/5/31-14:32
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
class ApiException1(throwable: Throwable?, var code: Int) : Exception(throwable) {
    override var message: String? = null
}