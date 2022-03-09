package com.work.login

import com.work.baselib.mvp.presenter.BasePresenter

class LoginPresenter : BasePresenter<LoginView, LoginModel>() {
    fun getTest() {
        var loginData = LoginData("请求数据")
        var loginModel = LoginModel("0", loginData)
        getBaseView().setData(loginModel)
    }
}