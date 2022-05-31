package com.work.httplib.dn.kt

interface ICallback<T> {
    fun onSuccess(data: T)
    fun onFailure(e: ApiException?)
}