package com.work.theIsle.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.toast.ToastUtils
import com.pgyer.pgyersdk.PgyerSDKManager


class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //Logger.addLogAdapter(AndroidLogAdapter())
        //74ccdc5e3947229728128b6e97f07128
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        initPgyerSDK()
        initToast()
        initArouter()
    }

    private fun initArouter() {
        ARouter.openDebug()
        ARouter.init(this)
    }

    private fun initToast() {
        ToastUtils.init(this)
    }

    private fun initPgyerSDK() {
        PgyerSDKManager.Init().setContext(this).start()
    }
}