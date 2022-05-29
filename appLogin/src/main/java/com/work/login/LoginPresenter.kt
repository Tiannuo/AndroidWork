package com.work.login

import com.work.baselib.mvp.presenter.BasePresenter
import com.work.httplib.httputils.HttpUtils
import com.work.login.api.UserApi
import com.work.login.bean.QingHuaBean
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter : BasePresenter<LoginView, QingHuaBean>() {
    @OptIn(DelicateCoroutinesApi::class)
    fun getTest() {
        val api: UserApi = HttpUtils.createApi(UserApi::class.java)
        var qingHuaBean: QingHuaBean?
        GlobalScope.launch {
            qingHuaBean = api.loadQing("json")
            //qingHuaBean.let { getBaseView().setData(it) }
            getBaseView().setData(qingHuaBean ?: QingHuaBean(0, ""))
        }

    }
}