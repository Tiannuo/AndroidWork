package com.work.httplib.dn.kt

interface KTICallback<T> {
    fun onSuccess(data: T)
    fun onFailure(e: KTApiException)
}