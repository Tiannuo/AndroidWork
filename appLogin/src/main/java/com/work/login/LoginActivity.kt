package com.work.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.activity.BaseMvpModelActivity
import com.work.baselib.arouter.RouterPath.PATH_LOGIN
import com.work.login.bean.QingHuaBean

@Route(path = PATH_LOGIN)
class LoginActivity :
    BaseMvpModelActivity<LoginView, LoginPresenter, ActivityLoginBinding, QingHuaBean>(),
    LoginView {

    override fun createBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun createPresenter() = LoginPresenter()

    override fun init() {
        getBinding()!!.btnTest.also { it.setOnClickListener { getPresenter()!!.getTest(getBinding()) } }
        getBinding()!!.btnCoroutine.also {
            it.setOnClickListener {
                getPresenter()!!.gotoCoroutine(
                    this
                )
            }
        }
    }

    override fun initData() {

    }

}
