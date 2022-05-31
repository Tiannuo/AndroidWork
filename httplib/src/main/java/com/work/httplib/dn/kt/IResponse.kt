package com.work.httplib.dn.kt

/**
 * @Author Administrator
 * @Date 2022/5/31-22:11
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
interface IResponse<T> {
    val content: T
    val msg: String
    val code: String
    val isSuccess: Boolean
}