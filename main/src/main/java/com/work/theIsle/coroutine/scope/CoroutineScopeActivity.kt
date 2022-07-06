package com.work.theIsle.coroutine.scope

import android.widget.Button
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_SCOPEACTIVITY
import com.work.httplib.httputils.HttpUtils
import com.work.login.bean.QingHuaBean
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import com.work.theIsle.api.UmogApi
import kotlinx.coroutines.*
import java.lang.Exception

/**o
 * @Author TIKOU
 * @Date 2022/7/5-14:30
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 结构化并发，作用域
 */
@Route(path = PATH_SCOPEACTIVITY)
class CoroutineScopeActivity : BaseActivity() {
    // MainScope() 工厂模式
    private val mainScope: CoroutineScope = MainScope()
    private var tvTest: TextView? = null
    private lateinit var btnTest: Button
    override fun initView() {
        setContentView(R.layout.activity_coroutine_scope)
        tvTest = findViewById(R.id.tv_test)
        btnTest = findViewById(R.id.btn_test)
        btnTest.setOnClickListener {
            getNetDataByLaunch()
            // getNetDataByRunBlocking()
        }
    }

    private fun getNetDataByRunBlocking() {
        val runBlocking: String = runBlocking {
            val qingHuaBean: QingHuaBean = HttpUtils.createApi(UmogApi::class.java).loadQing()
            tvTest!!.text = qingHuaBean.content
            // 此处阻塞会影响后续代码执行 runBlocking in 必须先执行完成，才会执行runBlocking after
            LoggerUtils.i("runBlocking in ${qingHuaBean.content}")
            // runBlocking 返回最后一行结果，默认不写是Unit
            "ok"
        }
        // 需等待runBlocking in 执行完成，再执行下面runBlocking after 注意和getNetDataByLaunch 做区分
        LoggerUtils.i("runBlocking after")
    }

    private fun getNetDataByLaunch() {
        // 结构化并发，挂起
        mainScope.launch {
            // 此处挂起10后执行后续操作,若此时销毁activity，即退出activity则会触发异常
            try {
                delay(3000)
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

    override fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}