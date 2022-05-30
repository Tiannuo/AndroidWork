package com.work.baselib.mvp.view

import androidx.viewbinding.ViewBinding

interface BaseView<M> {
    fun setData(binding: ViewBinding?, data: M?)
    fun setError(err: String)
}