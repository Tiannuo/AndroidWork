package com.work.theIsle.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.toast.ToastUtils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.pgyer.pgyersdk.PgyerSDKManager
import com.work.theIsle.dagger.*
import com.work.theIsle.hilt.httpProcessor.HttpHelper
import com.work.theIsle.hilt.httpProcessor.OkhttpProcessor
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {
    //DaggerSingletonComponent 持有的 module中的被Singleton修饰的对象数据全局共享，相当于于一个静态量
    private var daggerSingleComponent: DaggerSingletonComponent =
        DaggerDaggerSingletonComponent.builder()
            .httpDataModule(HttpDataModule())
            .presenterComponent(DaggerPresenterComponent.create())
            .resourcesComponent(DaggerResourcesComponent.create())
            .build()

    fun getDaggerSingleComponent() = daggerSingleComponent

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        initPgyerSDK()
        initToast()
        initArouter()
        initMultiDex()
        initLogger()
        initIHttpProcessor()

    }

    private fun initIHttpProcessor() {
        HttpHelper.init(OkhttpProcessor())
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