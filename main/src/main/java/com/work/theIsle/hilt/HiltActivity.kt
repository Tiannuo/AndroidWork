package com.work.theIsle.hilt

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.arouter.RouterPath.PATH_HILTACTIVITY
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import com.work.theIsle.hilt.interfacedi.TestInterface
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
@Route(path = PATH_HILTACTIVITY)
class HiltActivity : AppCompatActivity() {

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        }
    }
}