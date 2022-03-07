package com.work.baselib.mvp.presenter
import com.work.baselib.mvp.view.BaseView
open class BasePresenter<V: BaseView<M>,M> {

    private var mBaseView: V?=null

    fun bindView(mBaseView: V) {
        this.mBaseView = mBaseView
    }

    fun unBindView() {
        this.mBaseView = null
    }

    fun getBaseView() = mBaseView as V
}