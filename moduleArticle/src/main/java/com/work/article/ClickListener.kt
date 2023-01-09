package com.work.article

import android.view.View
import com.work.supportlib.LoggerUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author Admini
 * @Date 2023/1/5-17:44
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class ClickListener {
    fun clickTest(v: View, vm: IndexViewModel) {
        //LoggerUtils.e("clickTest= $msg")
        vm.getUser("Test")
    }

    fun clickTest2(v: View) {
        runBlocking {
            val time = measureTimeMillis {
                val one = async(Dispatchers.IO) {
                    doOne()
                }

                val two = async {
                    doTwo()
                }
                one.await().add(101)
                LoggerUtils.e("result one = ${one.await()} result two = ${two.await()}")
            }
            LoggerUtils.e("clickTest= $time")
        }

    }

    private suspend fun doOne(): MutableList<Int> {
        delay(1000)
        LoggerUtils.e("doOne= ${Thread.currentThread()}")
        return mutableListOf(1)
    }

    private suspend fun doTwo(): MutableList<Int> {
        delay(1000)
        LoggerUtils.e("doTwo= ${Thread.currentThread()}")
        return mutableListOf(2)
    }
}