package com.work.theIsle.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.toast.ToastUtils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
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
        initMultiDex()
        initLogger()
    }

    private fun initLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    private fun initMultiDex() {
        MultiDex.install(this);
    }

    private fun initArouter() {
        ARouter.openDebug()
        ARouter.openLog()
        ARouter.init(this)
    }

    private fun initToast() {
        ToastUtils.init(this)
    }

    private fun initPgyerSDK() {
        PgyerSDKManager.Init().setContext(this).start()
    }
}