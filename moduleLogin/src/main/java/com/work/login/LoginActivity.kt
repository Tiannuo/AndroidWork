package com.work.login

import com.alibaba.android.arouter.facade.annotation.Route
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

    override fun initView() {

    }

    override fun initData() {

    }

}
