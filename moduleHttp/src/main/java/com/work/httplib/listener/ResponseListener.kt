package com.work.httplib.listener

interface ResponseListener<T> {
    fun onSuccess(t: T)
    fun onFail(msg: String)
}