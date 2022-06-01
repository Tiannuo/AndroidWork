package com.work.httplib.common

/**

 * @Author Administrator
 * @Date 2022/5/31-15:04
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
interface Callback<T> {
    fun onSuccess(data: T)
    fun onFailure(e: ServerException)
}