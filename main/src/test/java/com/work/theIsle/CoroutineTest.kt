package com.work.theIsle

import com.orhanobut.logger.Logger
import kotlinx.coroutines.*
import okhttp3.internal.wait
import org.junit.Test
import kotlin.system.measureTimeMillis

/**

 * @Author Administrator
 * @Date 2022/5/30-17:00
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
class CoroutineTest {
    @Test
    fun test() = runBlocking {
        val job1 = launch {
            delay(200)
            Logger.d("job1 finished")
        }

        val job2 = async {
            delay(200)
            Logger.d("job2 finished")
            "job2 result"
        }
    }

    @Test
    fun testJoin() = runBlocking {
        val job1 = launch {
            delay(2000)
            println("job1 finished")
        }
        //job1.join() //join执行完成后才会执行其他job
        val job2 = launch {
            delay(200)
            println("job2 finished")
        }

        val job3 = launch {
            delay(200)
            println("job3 finished")
        }
    }

    @Test
    fun testAwait() = runBlocking {
        val job1 = async {
            delay(2000)
            println("job1 finished")
        }
        //job1.await()//await执行完成后才会执行其他job
        val job2 = async {
            delay(200)
            println("job2 finished")
        }

        val job3 = async {
            delay(200)
            println("job3 finished")
        }
    }

    @Test
    fun testSync() = runBlocking {
        val time = measureTimeMillis {
            val one = doOne()
            val two = doTwo()
            println("The Result:${one.plus(two)}")
        }
        println("Completed in $time ms")
    }

    @Test
    fun testCombineSync() = runBlocking {
        val time = measureTimeMillis {
            val one = async { doOne() }
            val two = async { doTwo() }
            println("The Result:${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }


    private suspend fun doOne(): Int {
        delay(1000)
        return 10
    }

    private suspend fun doTwo(): Int {
        delay(1000)
        return 20
    }

    @Test
    fun `test start mode`() = runBlocking {
        val job = launch(start = CoroutineStart.DEFAULT) {
            delay(5000)
            println("Job finished")
        }
        delay(1000)
        //job.cancel()

    }

    @Test
    fun `test start mode delay`() = runBlocking {
        val scope = CoroutineScope(Dispatchers.Default)
        val job1 = scope.launch {
            delay(100)
            println("job1")
        }

        job1.join()
        val job2 = scope.launch {
            delay(200)
            println("job2")
        }

        delay(100)
    }

    @Test
    fun `test start mode scope`() = runBlocking {
        val scope = CoroutineScope(Dispatchers.Default)
        val job1 = scope.launch {
            delay(1000)
            println("job1")
        }
        // 此处主线程走完直接结束了，而job1有自己的作用域，和runblocking的作用域是不同的，runblocking的作用域运行已经结束，而job1的代码还在挂起，所以挂起的job1已经不会走了
        //需要挂起runblocking等待job1的完成 delay()函数或者join等待作业执行完成
        //job1.join()
    }
}