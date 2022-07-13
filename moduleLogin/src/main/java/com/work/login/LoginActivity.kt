package com.work.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.activity.BaseMvpModelActivity
import com.work.baselib.arouter.RouterPath.PATH_DAGGERACTIVITY
import com.work.baselib.arouter.RouterPath.PATH_LOGIN
import com.work.baselib.arouter.RouterPath.PATH_UOMGDATA
import com.work.login.bean.QingHuaBean

@Route(path = PATH_LOGIN)
class LoginActivity :
    BaseMvpModelActivity<LoginView, LoginPresenter, ActivityLoginBinding, QingHuaBean>(),
    LoginView {

    override fun createBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun createPresenter() = LoginPresenter()

    override fun initView() {
        getBinding()!!.btnTest.also { it.setOnClickListener { getPresenter()!!.getTest(getBinding()) } }
        getBinding()!!.btnCoroutine.also {
            it.setOnClickListener {
                getPresenter()!!.gotoCoroutine(
                    this
                )
            }
        }
        getBinding()!!.btnUomg.setOnClickListener {
            ARouter.getInstance().build(PATH_UOMGDATA).navigation(this)
        }
        getBinding()!!.btnDagger.setOnClickListener {
            ARouter.getInstance().build(PATH_DAGGERACTIVITY).navigation(this)
        }
    }

    override fun initData() {

    }

}
