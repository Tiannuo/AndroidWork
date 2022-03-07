package com.work.baselib.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.work.baselib.mvp.presenter.BasePresenter
import com.work.baselib.mvp.view.BaseView
import androidx.viewbinding.ViewBinding

abstract class BaseMvpModelActivity<V: BaseView<M>, P : BasePresenter<V, M>, B:ViewBinding,M> : AppCompatActivity(), BaseView<M> {
    private var mPresenter: P? = null
    private var mBinding: B? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mPresenter == null) {
            mPresenter = createPresenter()
        }
        if (mBinding == null) {
            mBinding = createBinding()
            setContentView(getBinding()!!.root)
        }
        mPresenter!!.bindView(this as V)
        init()
        initData()
    }

    abstract fun createBinding(): B?

    protected abstract fun initData()

    protected abstract fun init()

    protected abstract fun createPresenter(): P


    fun getPresenter() = mPresenter

    fun getBinding() = mBinding

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.unBindView()
    }
}