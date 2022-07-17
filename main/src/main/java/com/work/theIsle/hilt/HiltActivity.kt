package com.work.theIsle.hilt

import android.widget.Button
import androidx.lifecycle.LifecycleObserver
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.arouter.RouterPath.PATH_HILTACTIVITY
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import com.work.theIsle.annotation.BindOkHttp
import com.work.theIsle.annotation.BindXutils
import com.work.theIsle.app.BaseAppActivity
import com.work.theIsle.hilt.httpProcessor.ICallback
import com.work.theIsle.hilt.httpProcessor.IHttpProcessor
import com.work.theIsle.hilt.interfacedi.TestInterface
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
@Route(path = PATH_HILTACTIVITY)
class HiltActivity : BaseAppActivity(),LifecycleObserver {

    @Inject
    lateinit var hiltObject: HiltObject

    @Inject
    lateinit var hiltObject2: HiltObject

    @Inject
    lateinit var hiltScopeObject: HiltScopeObject

    @Inject
    @Named("TestClass")
    lateinit var testInterface: TestInterface

    @Inject
    @Named("TestClass2")
    lateinit var testInterface2: TestInterface

    @Inject
    lateinit var hiltUser: HiltUser

    @Inject
    lateinit var testObject: TestObject

    lateinit var btnTest: Button

    @BindXutils
    @Inject
    lateinit var iHttpProcessor: IHttpProcessor

    @BindOkHttp
    @Inject
    lateinit var iHttpProcessor2: IHttpProcessor

    @BindOkHttp
    @Inject
    lateinit var iHttpProcessor3: IHttpProcessor


    override fun initView() {
        setContentView(R.layout.activity_hilt)
        btnTest = findViewById(R.id.btn_test)
        /*    HttpHelper.obtain()?.post("", mapOf(), object : ICallback {
                override fun onSuccess(result: String) {

                }

                override fun onFail(e: String) {

                }

            })*/
        LoggerUtils.i(hiltObject.hashCode())
        LoggerUtils.i(hiltObject2.hashCode())
        LoggerUtils.i(hiltScopeObject.hashCode())
        testInterface.method()
        testInterface2.method()
        btnTest.setOnClickListener {
            hiltUser.index = 10
            LoggerUtils.i("hiltUser index = ${hiltUser.index} & hiltUser name = ${hiltUser.name}")
            LoggerUtils.i(" testObject name = ${testObject.name}")
            LoggerUtils.i(" iHttpProcessor2  = ${iHttpProcessor2.hashCode()}")
            LoggerUtils.i(" iHttpProcessor3  = ${iHttpProcessor3.hashCode()}")
            iHttpProcessor.post("", mapOf(), object : ICallback {
                override fun onSuccess(result: String) {

                }

                override fun onFail(e: String) {

                }
            })

            iHttpProcessor2.post("", mapOf(), object : ICallback {
                override fun onSuccess(result: String) {

                }

                override fun onFail(e: String) {

                }
            })

            getBaseHttp().post("BaseHttp", mapOf(), null)
        }
    }

    override fun initData() {
        lifecycle.addObserver(this)
    }
}