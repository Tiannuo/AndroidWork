package com.work.login

import android.app.Activity
import com.alibaba.android.arouter.launcher.ARouter
import com.work.applogin.databinding.ActivityLoginBinding
import com.work.baselib.arouter.RouterPath
import com.work.baselib.mvp.view.BaseView
import com.work.login.bean.KotlinUserBean
import com.work.login.bean.QingHuaBean

interface LoginView : BaseView<ActivityLoginBinding, QingHuaBean> {

    override fun setData(binding: ActivityLoginBinding?, data: QingHuaBean?) {
        binding?.tvShowData?.text = data?.content
    }

    override fun setError(err: String) {

    }

    fun initView(binding: ActivityLoginBinding, loginPresenter: LoginPresenter) {

        binding.btnTest.also {
            it.setOnClickListener {

                loginPresenter.getTest(binding)
            }
        }
        binding.btnCoroutine.also {
            it.setOnClickListener {
                ARouter.getInstance().build("/coroutineTo/CoroutineActivity3")
                    .navigation(this@LoginView as Activity)
            }
        }
        binding.btnUomg.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PATH_UOMGDATA).navigation()
        }
        binding.btnDagger.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PATH_DAGGERACTIVITY).navigation()
        }

        binding.btnHilt.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PATH_HILTACTIVITY).navigation()
        }

        binding.btnJetpack.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PATH_JETPACKACTIVITY).navigation()
        }

        binding.btnKotlin.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PATH_KOTLINACTIVITY)
                .withObject("key", KotlinUserBean("kotlin"))
                .navigation()
        }

        binding.btnGlide.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PATH_GLIDEACTIVITY)
                .navigation()
        }

        binding.btnArticle.setOnClickListener{
            ARouter.getInstance().build(RouterPath.PATH_KOTLININDEXACTIVITY)
                .navigation()
        }
    }
}