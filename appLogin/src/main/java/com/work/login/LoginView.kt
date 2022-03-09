package com.work.login

import com.work.baselib.mvp.view.BaseView

interface LoginView : BaseView<LoginModel> {
    override fun setData(data: LoginModel) {

    }

    override fun setError(err: String) {

    }
}