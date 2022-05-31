package com.work.httplib.common

/**
 * @Author Administrator
 * @Date 2022/5/31-14:17
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
object Error {
    /**
     * 未知错误
     */
    const val UNKNOWN = 1000

    /**
     * 解析错误
     */
    const val PARSE_ERROR = 1001

    /**
     * 网络错误
     */
    const val NETWORD_ERROR = 1002

    /**
     * 协议出错
     */
    const val HTTP_ERROR = 1003
}