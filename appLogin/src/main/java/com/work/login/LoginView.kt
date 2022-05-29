package com.work.login

import com.work.baselib.mvp.view.BaseView
import com.work.login.bean.QingHuaBean

interface LoginView : BaseView<QingHuaBean> {
    override fun setData(data: QingHuaBean?) {

    }

    override fun setError(err: String) {

    }
}