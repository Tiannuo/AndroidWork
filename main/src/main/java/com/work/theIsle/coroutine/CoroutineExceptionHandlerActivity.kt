package com.work.theIsle.coroutine

import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_EXCEOTIONHANDLERACTIVITY
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import com.work.theIsle.databinding.ActivityCoroutineExceptionHandlerBinding
import kotlinx.coroutines.*

/**
 * @Author TIKOU
 * @Date 2022/7/7-14:13
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 协程上下文异常处理
 * 子协程的定义：在同一个Scope下调用的，或者在根协程内部继续调用async，launch等新建协程的方式
 * Job()和SupervisorJob()
 * 所谓的协程的异常传播，是指的主动捕捉异常后的，其他的子协程是否会收到影响，而不是仅仅抛出异常的情况
 * Job() 抛出异常后主动捕捉，子协程一旦捕捉到异常，其父协程会通知所有子协程取消，最后取消自己
 * SupervisorJob() 抛出异常后主动捕捉，子协程一旦捕捉到异常，只会取消自己，其父协程和兄弟协程正常运行
 * CoroutineExceptionHandler异常捕获处理满足的条件是
 * 1：自动抛出的异常 launch，而 async不行
 * 2：CoroutineScope的CoroutineContext中或者一个根协程（一级子协程）
 */
@Route(path = PATH_EXCEOTIONHANDLERACTIVITY)
class CoroutineExceptionHandlerActivity : BaseActivity() {
    private var binding: ActivityCoroutineExceptionHandlerBinding? = null
    override fun initView() {
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_coroutine_exception_handler
        )
        binding!!.btnTest.setOnClickListener {
            productExceptionGlobalHandler()
        }
    }

    override fun initData() {
        //productException()
        //productChildException()
        //productChildSupervisorJobException()
        //productChildSupervisorScopeException()
        //productExceptionHandler()
        //productExceptionHandler2()
        //productExceptionGlobalHandler()
    }

    private fun productExceptionGlobalHandler() {
        GlobalScope.launch {
            "abc".substring(100)
        }

    }

    private fun productExceptionHandler2() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, throwable ->
                LoggerUtils.i("Caught CoroutineExceptionHandler ${throwable.message}")
            }
            val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

            val deffer = scope.launch(handler) {
                throw IllegalArgumentException("deffer IllegalArgumentException")
            }
            deffer.join()
            val child = scope.async {
                throw IllegalArgumentException("child IllegalArgumentException")
            }
            try {
                child.await()
            } catch (e: Exception) {
                LoggerUtils.i("Caught try ${e.message}")
            }

        }

    }

    /**
     * job AssertionError 可以被捕获，满足CoroutineExceptionHandler处理条件，并且由其处理
     * deferred AssertionError 需要手动处理
     * 具体参见productExceptionHandler2
     */
    private fun productExceptionHandler() {
        runBlocking {
            val handler = CoroutineExceptionHandler { _, throwable ->
                LoggerUtils.i("Caught CoroutineExceptionHandler ${throwable.message}")
            }
            val job = GlobalScope.launch(handler) {
                throw AssertionError("job AssertionError")
            }
            val deferred = GlobalScope.async(handler) {
                throw AssertionError("deferred AssertionError")
            }
            job.join()
            deferred.await()
        }
    }

    /**
     * 参考和SupervisorJob(),效果一致
     */
    private fun productChildSupervisorScopeException() {
        runBlocking {
            supervisorScope {
                val child = launch {
                    try {
                        LoggerUtils.i("child is sleeping")
                        delay(10000)
                    } catch (e: Exception) {
                        LoggerUtils.i("child is cancelled ${e.message}")
                    } finally {
                        LoggerUtils.i("child is cancelled finally")
                    }
                }
                yield()
                LoggerUtils.i("throw an exception by yield")
                try {
                    throw IllegalArgumentException("IllegalArgumentException")
                } catch (e: Exception) {

                }

            }

        }
    }

    /**
     * 子协程自我处理，不会影响其他子协程的业务
     *
     */
    private fun productChildSupervisorJobException2() {

        runBlocking {
            val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

            val deffer = scope.async {
                //SupervisorJob的协程传播 此处子协程抛出异常后，只会此协程自我取消，不会影响其父协程以及兄弟协程
                throw IllegalArgumentException("IllegalArgumentException")
            }

            try {
                // delay会引起上个界面跳转的延迟时间
                delay(300)
                deffer.await()
            } catch (e: Exception) {
                // 会抛出协程被取消的异常
                LoggerUtils.e("Caught ${e.message}")
            } finally {
                LoggerUtils.e("Caught finally")
            }

            val child = scope.launch {
                delay(400)
                LoggerUtils.e("child launch")
            }

            child.join()
        }
    }


    /**
     * 子协程自我处理，不会影响其他子协程的业务
     *
     */
    private fun productChildSupervisorJobException() {

        runBlocking {
            val supervisorScope = CoroutineScope(SupervisorJob())
            val job1 = supervisorScope.launch {
                delay(1000)
                LoggerUtils.i("job 1")
                try {
                    throw IllegalArgumentException()
                } catch (e: Exception) {
                    LoggerUtils.i("job1 IllegalArgumentException")
                }

            }
            val job2 = supervisorScope.launch {
                try {
                    delay(5000)
                } catch (e: Exception) {

                } finally {
                    LoggerUtils.i("job2")
                }
            }
            joinAll(job2, job1)
        }
    }

    /**
     * 非根协程，即是一级(根)协程代码下的子协程，其他协程产生的异常 总是会被传播
     * 默认传播异常特性
     */
    private fun productChildException() {
        runBlocking {
            val scope = CoroutineScope(Job())
            // launch 一级(根)协程
            val job = scope.launch {
                // async 非根协程

                val deffer = async {
                    //默认的协程传播 此处子协程抛出异常后，其父协程下的所有子协程都会被取消，并且将异常的信息传播传递到父协程中，最后所有子协程均处理完毕后父协程自我取消
                    throw IllegalArgumentException()
                }

                try {
                    // delay会引起上个界面跳转的延迟时间
                    delay(3000)
                } catch (e: Exception) {
                    // 会抛出协程被取消的异常
                    LoggerUtils.e("Caught ${e.message}")
                }
            }
            job.join()
        }
    }

    /**
     * 根协程，即是一级协程代码
     */
    @OptIn(DelicateCoroutinesApi::class)
    private fun productException() {
        runBlocking {
            val job = GlobalScope.launch() {
                try {
                    throw IndexOutOfBoundsException()
                } catch (e: Exception) {
                    LoggerUtils.e("Caught IndexOutOfBoundsException")
                }
            }
            job.join()

            val deferred = GlobalScope.async {
                throw ArithmeticException()
            }
            try {
                deferred.await()
            } catch (e: Exception) {
                LoggerUtils.e("Caught ArithmeticException")
            }
        }
        /**
         * 异常传播的总结
         * 自动传播异常：launch 异常捕捉需要在 throw 的时候，即可能发生错误的协程代码
         * 用户主动异常：sync 异常捕捉在 throw 或者 await（即主动消费的时候）都可以进行捕捉
         * 因此看出用户主动消费异常更容易操控整个sync协程代码的捕捉异常，而自动传播异常 launch 则需要明确捕捉出异常的部分协程代码
         */
    }
}