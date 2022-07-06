package com.work.theIsle.coroutine.scope

import android.widget.Button
import android.widget.TextView
import com.work.baselib.activity.BaseActivity
import com.work.httplib.httputils.HttpUtils
import com.work.login.bean.QingHuaBean
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import com.work.theIsle.api.UmogApi
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * @Author TIKOU
 * @Date 2022/7/6-17:12
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description kotlin委托实现Scope
 * CoroutineScope by MainScope()
 */
class CoroutineScopeByActivity : BaseActivity(), CoroutineScope by MainScope() {

    private var tvTest: TextView? = null
    private lateinit var btnTest: Button

    private fun getNetDataByLaunch() {
        // 结构化并发，挂起
        this.launch {
            // 此处挂起10后执行后续操作
            try {
                delay(10000)
            } catch (e: Exception) {
                e.printStackTrace()
                e.message?.let { LoggerUtils.i(it) }
            }
            val qingHuaBean: QingHuaBean = HttpUtils.createApi(UmogApi::class.java).loadQing()
            tvTest!!.text = qingHuaBean.content
            // 此处挂起并不影响后续代码先执行 launch after 然后经过网络请求回来的数据才会执行launch in
            LoggerUtils.i("launch in ${qingHuaBean.content}")
        }
        // 此launch after 先执行
        LoggerUtils.i("launch after")
    }

    override fun initView() {
        setContentView(R.layout.activity_coroutine_scope)
        tvTest = findViewById(R.id.tv_test)
        btnTest = findViewById(R.id.btn_test)
        btnTest.setOnClickListener {
            getNetDataByLaunch()
        }
    }

    override fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        this.cancel()
    }
}