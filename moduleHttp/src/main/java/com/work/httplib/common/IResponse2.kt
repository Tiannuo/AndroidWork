package com.work.httplib.common

/**

 * @Author Administrator
 * @Date 2022/5/31-13:46
 * @Email wangweitikou1994@gmail.com
 * @Des 响应数据
 */
interface IResponse2<T> {
    fun getData(): T
    fun getCode(): String
    fun getMsg(): String
}