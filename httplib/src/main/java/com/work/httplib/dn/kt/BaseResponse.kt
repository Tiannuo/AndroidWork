package com.work.httplib.dn.kt

/**

 * @Author Administrator
 * @Date 2022/5/31-21:57
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
class BaseResponse<T> : IResponse<T> {
    override val content: T
        get() = TODO("Not yet implemented")
    override val msg: String?
        get() = TODO("Not yet implemented")
    override val code: String?
        get() = TODO("Not yet implemented")
}