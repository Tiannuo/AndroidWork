package com.work.login

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.mvp.view.BaseView
import com.work.login.bean.QingHuaBean

interface LoginView : BaseView<QingHuaBean> {

    override fun setData(binding: ViewBinding?, data: QingHuaBean?) {
        (binding as ActivityLoginBinding).tvShowData.text = data?.content
    }

    override fun setError(err: String) {

    }

    fun gotoCoroutine(context: Context) {
        ARouter.getInstance().build("/coroutineTo/CoroutineActivity").navigation(context)
    }
}