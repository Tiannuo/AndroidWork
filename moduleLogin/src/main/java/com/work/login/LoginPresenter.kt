package com.work.login

import android.app.Activity
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.arouter.RouterPath
import com.work.baselib.mvp.presenter.BasePresenter
import com.work.httplib.httputils.HttpUtils
import com.work.login.api.UserApi
import com.work.login.bean.KotlinUserBean
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

    /*
    * 跳转到协程
    * */
    private fun gotoCoroutine(context: Context) {
        getBaseView().gotoCoroutine(context)
    }

    private fun gotoJetpack() {
        ARouter.getInstance().build(RouterPath.PATH_JETPACKACTIVITY).navigation()
    }

    /**
     * 跳转到Kotlin
     * @param
     */
    private fun gotoKotlin() {
        ARouter.getInstance().build(RouterPath.PATH_KOTLINACTIVITY)
            .withObject("key", KotlinUserBean("kotlin"))
            .navigation()
    }

    /**
     * 初始化view的部分相关信息
     * @param mBinding ActivityLoginBinding?
     */
    override fun initView(activity: Activity, mBinding: ActivityLoginBinding?) {

        mBinding!!.btnTest.also { it.setOnClickListener { getTest(mBinding) } }
        mBinding.btnCoroutine.also {
            it.setOnClickListener {
                gotoCoroutine(
                    activity
                )
            }
        }
        mBinding.btnUomg.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PATH_UOMGDATA).navigation(activity)
        }
        mBinding.btnDagger.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PATH_DAGGERACTIVITY).navigation(activity)
        }

        mBinding.btnHilt.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PATH_HILTACTIVITY).navigation()
        }

        mBinding.btnJetpack.setOnClickListener {
            gotoJetpack()
        }

        mBinding.btnKotlin.setOnClickListener {
            gotoKotlin()
        }
    }
}