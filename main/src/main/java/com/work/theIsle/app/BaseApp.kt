package com.work.theIsle.app

import android.app.Application
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.toast.Toaster
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.pgyer.pgyersdk.PgyerSDKManager
import com.work.theIsle.annotation.BindXutils
import com.work.theIsle.dagger.*
import com.work.theIsle.hilt.httpProcessor.HttpHelper
import com.work.theIsle.hilt.httpProcessor.IHttpProcessor
import com.work.theIsle.hilt.httpProcessor.OkhttpProcessor
import com.work.theIsle.jetpack.observer.ApplicationObserver
import dagger.hilt.android.HiltAndroidApp
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import javax.inject.Inject

@HiltAndroidApp
class BaseApp : Application() {

    @BindXutils
    @Inject
    lateinit var baseHttp: IHttpProcessor

    private lateinit var flutterEngine: FlutterEngine

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

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
        initFlutterConfig()
    }

    private fun initFlutterConfig() {
        flutterEngine = FlutterEngine(this)
        flutterEngine.navigationChannel.setInitialRoute("main/homePage")
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        FlutterEngineCache.getInstance().put("FlutterDemo",flutterEngine)
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
        Toaster.init(this)
    }

    private fun initPgyerSDK() {
        PgyerSDKManager.Init().setContext(this).start()
    }

    override fun onTerminate() {
        flutterEngine.destroy()
        super.onTerminate()
    }
}