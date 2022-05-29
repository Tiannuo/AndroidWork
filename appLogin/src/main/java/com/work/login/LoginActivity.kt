package com.work.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.activity.BaseMvpModelActivity
import com.work.login.bean.QingHuaBean

@Route(path = "/loginTo/loginActivity")
class LoginActivity :
    BaseMvpModelActivity<LoginView, LoginPresenter, ActivityLoginBinding, QingHuaBean>(),
    LoginView {

    override fun createBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun createPresenter() = LoginPresenter()

    override fun init() {
        getBinding()!!.btnTest.also { it.setOnClickListener { getPresenter()!!.getTest() } }
    }

    override fun initData() {

    }

    override fun setData(data: QingHuaBean?) {
        super.setData(data)
        getBinding()?.tvShowData?.text = data?.content
    }

    override fun setError(err: String) {
        super.setError(err)
    }
}
