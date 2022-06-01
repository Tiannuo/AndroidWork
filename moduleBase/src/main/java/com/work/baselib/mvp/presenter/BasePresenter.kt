package com.work.baselib.mvp.presenter

import androidx.viewbinding.ViewBinding
import com.work.baselib.mvp.view.BaseView

open class BasePresenter<V : BaseView<B, M>, B : ViewBinding, M> {

    private var mBaseView: V? = null

    fun bindView(mBaseView: V) {
        this.mBaseView = mBaseView
    }

    fun unBindView() {
        this.mBaseView = null
    }

    fun getBaseView() = mBaseView as V
}