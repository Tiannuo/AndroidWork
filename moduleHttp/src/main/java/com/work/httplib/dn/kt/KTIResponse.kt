package com.work.httplib.dn.kt

/**
 * @Author Administrator
 * @Date 2022/5/31-22:11
 * @Email wangweitikou1994@gmail.com
 * @Des 此处kotlin设计的很妙啊，interface并不需要写set get 默认用属性实现，子类实现更清爽
 */
interface KTIResponse<T> {
    val content: T?
    val msg: String
    val code: String
    val isSuccess: Boolean
}