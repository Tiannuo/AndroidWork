package com.work.login

import android.app.Activity
import com.work.applogin.databinding.ActivityLoginBinding
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
        var qingHuaBean: QingHuaBean?
        // 不入参默认是子线程
        GlobalScope.launch(Dispatchers.Main) {
            qingHuaBean = api.loadQing()
            //qingHuaBean.let { getBaseView().setData(it) }
            getBaseView().setData(binding, qingHuaBean ?: QingHuaBean(0, "哈哈哈"))
        }

    }


    /**
     * 初始化view的部分相关信息
     * @param mBinding ActivityLoginBinding?
     */
    override fun initView(activity: Activity, mBinding: ActivityLoginBinding?) {
        if (mBinding != null) {
            getBaseView().initView(mBinding,this)
        }
    }
}