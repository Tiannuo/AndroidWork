package com.work.theIsle.dagger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.arouter.RouterPath.PATH_DAGGERACTIVITY
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import com.work.theIsle.app.BaseApp
import dagger.Lazy
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

@Route(path = PATH_DAGGERACTIVITY)
class DaggerActivity : AppCompatActivity() {
    @Inject
    lateinit var httpDataObject1: HttpDataObject

    @Inject
    lateinit var httpDataObject2: HttpDataObject

    @Inject
    lateinit var presenter1: Presenter

    @Inject
    lateinit var presenter2: Presenter

    @Named("YoungUser")
    @Inject
    lateinit var youngUser: User

    @Named("OldUser")
    @Inject
    lateinit var oldUser: User

    @Inject
    lateinit var resourcesObj: ResourcesObject

    @Named("key1")
    @Inject
    lateinit var stubUser: StubUser

    @Named("key2")
    @Inject
    lateinit var stubUser2: StubUser

    @Named("YoungUser")
    @Inject
    lateinit var userLazy: Lazy<User>

    @Named("YoungUser")
    @Inject
    lateinit var userProvide: Provider<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)
        // dagger注入方式一
        //DaggerDaggerComponent.create().injectActivity(this)
        //全局单例注入
        (application as BaseApp).getDaggerSingleComponent().injectActivity(this)
        LoggerUtils.i("DaggerActivityCode" + httpDataObject1.hashCode())
        LoggerUtils.i("DaggerActivityCode" + httpDataObject2.hashCode())
        findViewById<View>(android.R.id.content).postDelayed(Runnable {
            val intent = Intent(this@DaggerActivity, DaggerActivity2::class.java)
            startActivity(intent)
        }, 2000)
    }

    override fun onPause() {
        super.onPause()
        httpDataObject1.str = "onPause"
        LoggerUtils.i("DaggerActivityCode" + httpDataObject2.str)
        LoggerUtils.i("DaggerActivityCode" + httpDataObject2.str)
        LoggerUtils.i("DaggerActivityCode" + presenter1.hashCode())
        LoggerUtils.i("DaggerActivityCode" + presenter2.hashCode())
        LoggerUtils.i("DaggerActivityCode" + youngUser.name)
        LoggerUtils.i("DaggerActivityCode" + oldUser.name)
        LoggerUtils.i("DaggerActivityCode" + resourcesObj.hashCode())
        LoggerUtils.i("DaggerActivityCode stubUser" + stubUser.index)
        LoggerUtils.i("DaggerActivityCode stubUser" + stubUser2.index)
        LoggerUtils.i("DaggerActivityCode user" + userLazy.get().hashCode())
        LoggerUtils.i("DaggerActivityCode user" + userProvide.get().hashCode())
        LoggerUtils.i("DaggerActivityCode onPause")
    }

}