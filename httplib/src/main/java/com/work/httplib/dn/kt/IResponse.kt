package com.work.httplib.dn.kt

/**
 * 具体的返回的类型数据不知道：
 * 通过接口的形式来转换兼容
 * @param <T>
</T> */
interface IResponse<T> {
    val content: T
    val msg: String?
    val code: String?
}