package com.work.login

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.mvp.view.BaseView
import com.work.login.bean.QingHuaBean

interface LoginView : BaseView<ActivityLoginBinding, QingHuaBean> {

    override fun setData(binding: ActivityLoginBinding?, data: QingHuaBean?) {
        binding?.tvShowData?.text = data?.content
    }

    override fun setError(err: String) {

    }

    fun gotoCoroutine(context: Context) {
        ARouter.getInstance().build("/coroutineTo/CoroutineActivity3").navigation(context)
    }
}