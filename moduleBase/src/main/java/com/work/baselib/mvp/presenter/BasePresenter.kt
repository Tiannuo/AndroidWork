package com.work.baselib.mvp.presenter

import android.app.Activity
import androidx.viewbinding.ViewBinding
import com.work.baselib.mvp.view.BaseView

abstract class BasePresenter<V : BaseView<B, M>, B : ViewBinding, M> {

    private var mBaseView: V? = null

    fun bindView(mBaseView: V) {
        this.mBaseView = mBaseView
    }

    fun unBindView() {
        this.mBaseView = null
    }

    fun getBaseView() = mBaseView as V

    /**
     * Presenter 中初始化activity 初始化view层的代码
     * @param activity Activity
     * @param mBinding B?
     */
    abstract fun initView(activity: Activity, mBinding: B?)
}