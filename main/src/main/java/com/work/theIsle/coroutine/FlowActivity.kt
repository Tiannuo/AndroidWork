package com.work.theIsle.coroutine

import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_FLOWACTIVITY
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

/**
 * @Author TIKOU
 * @Date 2022/7/4-18:13
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@Route(path = PATH_FLOWACTIVITY)
class FlowActivity : BaseActivity() {
    override fun initView() {
        setContentView(R.layout.activity_flow)
    }

    override fun initData() {
        //showTestValues()
        //showSequence()
        testMultipleValue3()
    }

    fun testMultipleValue2() = runBlocking {

    }


    private fun testMultipleValue3() = runBlocking() {

        simpleFlow().collect {
            LoggerUtils.i(it)
        }
    }

    private fun showSequence() {
        val sequence: Sequence<Int> = sequence {
            yieldAll(1..3)
        }

        sequence.iterator().forEach {
            LoggerUtils.i(it)
        }
    }

    private fun showTestValues() {
        simpleList().forEach {
            LoggerUtils.i(it)
        }
    }

    private fun simpleList(): List<Int> = listOf(1, 2, 3, 4)
    private suspend fun simpleList2(): List<Int> {
        delay(1000)
        return listOf(1, 2, 3)
    }

    private fun simpleFlow() = flow<Int> {
        for (i in 1..3) {
            delay(5000)
            emit(i)
        }
    }.flowOn(Dispatchers.Default)
}