package com.work.baselib.mvp.view

interface BaseView<M> {
    fun setData(data: M?)
    fun setError(err: String)
}