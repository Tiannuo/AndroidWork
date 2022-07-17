package com.work.login

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.arouter.RouterPath
import com.work.baselib.mvp.presenter.BasePresenter
import com.work.httplib.httputils.HttpUtils
import com.work.login.api.UserApi
import com.work.login.bean.QingHuaBean
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter : BasePresenter<LoginView, ActivityLoginBinding, QingHuaBean>() {
    /*
    * 测试网络数据请求
    * */
    @OptIn(DelicateCoroutinesApi::class)
    fun getTest(binding: ActivityLoginBinding?) {
        val api: UserApi = HttpUtils.createApi(UserApi::class.java)
        var qingHuaBean: QingHuaBean? = null
        // 不入参默认是子线程
        GlobalScope.launch(Dispatchers.Main) {
            qingHuaBean = api.loadQing()
            //qingHuaBean.let { getBaseView().setData(it) }
            getBaseView().setData(binding, qingHuaBean ?: QingHuaBean(0, "哈哈哈"))
        }

    }

    /*
    * 跳转到协程
    * */
    fun gotoCoroutine(context: Context) {
        getBaseView().gotoCoroutine(context)
    }

    fun gotoJetpack(loginActivity: LoginActivity) {
        ARouter.getInstance().build(RouterPath.PATH_JETPACKACTIVITY).navigation()
    }
}