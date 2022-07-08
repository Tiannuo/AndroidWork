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
 * Job：控制协程的生命周期 默认Job()
 * CoroutineDispatcher：分发协程的线程任务 默认Dispatcher.Default
 * CoroutineName： 协程名称调试使用 默认 coroutine
 * CoroutineExceptionHandler：处理未被捕获的异常 默认CoroutineExceptionHandler
 *
 * async {  }.await() = withContext(){}
 *
 * 协程的继承，参开于类继承，上溯四个部分即是属性，
 * 子类CoroutineContext 会继承父类协程属性，子类定义走子类属性值，子类未定义走父类属性值，父类未定义走默认值
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