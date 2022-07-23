package com.work.baselib.activity

import androidx.viewbinding.ViewBinding
import com.work.baselib.mvp.presenter.BasePresenter
import com.work.baselib.mvp.view.BaseView

abstract class BaseMvpModelActivity<V : BaseView<B, M>, P : BasePresenter<V, B, M>, B : ViewBinding, M> :
    BaseActivity(), BaseView<B, M> {
    private var mPresenter: P? = null
    private var mBinding: B? = null

    override fun initBaseMvpModel() {
        super.initBaseMvpModel()
        if (mPresenter == null) {
            mPresenter = createPresenter()
        }
        if (mBinding == null) {
            mBinding = createBinding()
            setContentView(getBinding()!!.root)
        }
        mPresenter!!.bindView(this as V)
        mPresenter!!.initView(this,mBinding)
    }

    abstract fun createBinding(): B?
    protected abstract fun createPresenter(): P
    abstract override fun initView()
    abstract override fun initData()
    fun getPresenter() = mPresenter

    fun getBinding() = mBinding

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unBindView()
    }
}