package com.work.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.activity.BaseMvpModelActivity
@Route(path = "/loginTo/loginActivity")
class LoginActivity :
    BaseMvpModelActivity<LoginView, LoginPresenter, ActivityLoginBinding, LoginModel>(), LoginView {

    override fun createBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun createPresenter() = LoginPresenter()

    override fun init() {
        getBinding()!!.btnLogin.setOnClickListener { getPresenter()!!.getTest() }
    }

    override fun initData() {

    }

    override fun setData(data: LoginModel) {
        super.setData(data)
        //getBinding().btnLogin
    }

    override fun setError(err: String) {
        super.setError(err)
    }
}
