package com.work.login

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.mvp.presenter.BasePresenter
import com.work.httplib.httputils.HttpUtils
import com.work.login.api.UserApi
import com.work.login.bean.QingHuaBean
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter : BasePresenter<LoginView, QingHuaBean>() {
    /*
    * 测试网络数据请求
    * */
    @OptIn(DelicateCoroutinesApi::class)
    fun getTest(binding: ActivityLoginBinding?) {
        val api: UserApi = HttpUtils.createApi(UserApi::class.java)
        var qingHuaBean: QingHuaBean?
        GlobalScope.launch {
            qingHuaBean = api.loadQing("json")
            //qingHuaBean.let { getBaseView().setData(it) }
            getBaseView().setData(binding,qingHuaBean ?: QingHuaBean(0, ""))
        }

    }

    /*
    * 跳转到协程
    * */
    fun gotoCoroutine(context: Context) {
        getBaseView().gotoCoroutine(context)
    }
}