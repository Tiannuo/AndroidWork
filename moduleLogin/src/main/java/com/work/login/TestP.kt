package com.work.login

import android.app.Activity
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.mvp.presenter.BasePresenter
import com.work.login.bean.QingHuaBean

class TestP:BasePresenter<LoginView,ActivityLoginBinding,QingHuaBean>() {
    override fun initView(activity: Activity, mBinding: ActivityLoginBinding?) {

    }
}