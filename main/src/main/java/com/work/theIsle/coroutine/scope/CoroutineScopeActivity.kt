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
import okhttp3.internal.wait
import java.lang.Exception
import kotlin.system.measureTimeMillis

/**o
 * @Author TIKOU
 * @Date 2022/7/5-14:30
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 结构化并发，作用域
 */
@Route(path = PATH_SCOPEACTIVITY)
class CoroutineScopeActivity : BaseActivity(), CoroutineScope by MainScope() {
    // MainScope() 工厂模式
    private val mainScope: CoroutineScope = MainScope()
    private var tvTest: TextView? = null
    private lateinit var btnTest: Button
    private lateinit var btnScope: Button
    private lateinit var btnZyy: Button
    private lateinit var btnBhyc: Button
    override fun initView() {
        setContentView(R.layout.activity_coroutine_scope)
        tvTest = findViewById(R.id.tv_test)
        btnTest = findViewById(R.id.btn_test)
        btnScope = findViewById(R.id.btn_scope)
        btnZyy = findViewById(R.id.btn_zyy)
        btnBhyc = findViewById(R.id.btn_bhyc)
        btnTest.setOnClickListener {
            getNetDataByLaunch()
            // getNetDataByRunBlocking()
        }
        btnScope.setOnClickListener {

/*            mainScope.launch {
                val time = measureTimeMillis {
                    testScope()
                }
                LoggerUtils.e("mainScope time = $time")
                // 2s
            }*/

            runBlocking {
                val time = measureTimeMillis {
                    testScope()
                }
                LoggerUtils.e("runBlocking time = $time")
                // 2s
            }
        }

        btnZyy.setOnClickListener {
            //testZyy()
            testGlobalScope()
        }

        btnBhyc.setOnClickListener {

            val handler = CoroutineExceptionHandler { _, throwable ->
                LoggerUtils.e("Cauth ${throwable.message}")

            }
            MainScope().launch(Dispatchers.IO+Job()+CoroutineName("Test")) {
                val index = "abc"
                index.substring(100)
            }
        }
    }


    private fun testGlobalScope() = runBlocking<Unit> {

        val job = GlobalScope.launch {
            delay(1000)
            LoggerUtils.e("job 1")
        }
    }

    private fun testZyy() {
        // Android的主线程是不会中断的，一直运行，除非app死了，所以 在CoroutineTest 中的test start mode scope    println("job1") 是不会答应的，只有将协程挂起或者job.join进去才会打印，如下LoggerUtils.e("testZyy job1")Android的主线程是一直运行的，所以会答应
        runBlocking<Unit> {
            val scope = CoroutineScope(Dispatchers.IO + CoroutineName("Test") + Job())
            val job = scope.launch {
                delay(1000)
                LoggerUtils.e("testZyy job1")
            }

            val job2 = scope.launch {
                delay(2000)
                LoggerUtils.e("testZyy job2")
            }
            //job.join()
            //job2.join()
        }
    }

    private suspend fun testScope() {
        /*
        *
        * 由 async方法启动的协程，对于 supervisorScope，如果不调用 async的 await方法，协程就不会抛异常，如果调用，则会抛异常，但不管是否抛异常，其余子协程和整个 scope都可正常执行；
        * 对于 coroutineScope，只要某个子协程发生异常，就会影响整个 scope和其余子协程的执行。
        * */
        supervisorScope {
            val job1 = async {
                delay(1000)
                repeat(1000) { i: Int ->
                    LoggerUtils.e("i= $i")

                }
                LoggerUtils.e("job 1 finished")

            }

            val job2 = async {
                delay(2000)
                LoggerUtils.e("job 2 finished")
                //throw java.lang.IllegalArgumentException()
            }
            job1.await()
            job2.await()
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
        launch {
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
        cancel()
    }
}