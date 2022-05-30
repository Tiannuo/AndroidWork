package com.work.baselib.mvp.view

import androidx.viewbinding.ViewBinding

interface BaseView<B : ViewBinding, M> {
    fun setData(binding: B?, data: M?)
    fun setError(err: String)
}