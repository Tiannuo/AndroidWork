package com.work.httplib.common

/**
 * @Author Administrator
 * @Date 2022/5/31-14:21
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
class ServerException(var code: String, override var message: String):Exception()