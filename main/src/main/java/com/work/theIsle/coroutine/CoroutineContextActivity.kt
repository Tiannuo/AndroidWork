package com.work.theIsle.coroutine

import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_CONTEXTACTIVITY
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import kotlinx.coroutines.*

/**
 * @Author TIKOU
 * @Date 2022/7/5-11:57
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 * CoroutineContext 一组用于定义协程行为的元素，由以下四个部分组成
 * Job：控制协程的生命周期
 * CoroutineDispatcher：分发协程的线程任务
 * CoroutineName： 协程名称调试使用
 * CoroutineExceptionHandler：处理未被捕获的异常
 *
 * async {  }.await() = withContext(){}
 */
@Route(path = PATH_CONTEXTACTIVITY)
class CoroutineContextActivity : BaseActivity() {

    override fun initView() {
        setContentView(R.layout.activity_coroutine_context)
    }

    override fun initData() {
        //testCoroutineContext()
        testCoroutineContextExtend()
    }

    private fun testCoroutineContext() = runBlocking {
        launch(Dispatchers.Default + CoroutineName("test")) {
            LoggerUtils.i("Im working in thread ${Thread.currentThread().name}")
        }
    }

    private fun testCoroutineContextExtend() = runBlocking {
        val scope = CoroutineScope(Job() + Dispatchers.IO + CoroutineName("test"))
        val job = scope.launch {
            LoggerUtils.i("${coroutineContext[Job]} ${Thread.currentThread().name}")
            val result = withContext(Dispatchers.IO) {
                LoggerUtils.i("result ${coroutineContext[Job]} ${Thread.currentThread().name}")
                "Ok"
            }

        }
        job.join()
    }

}