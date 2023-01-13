package com.work.theIsle.coroutine.flow

import com.work.supportlib.LoggerUtils
import com.work.theIsle.coroutine.vm.FlowPracticeVM
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * @Author Admini
 * @Date 2023/1/9-18:39
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description Repository 负责最后的数据整合
 * Flow的三种实现方式
 */
class FlowRepository(private val viewModelScope: CoroutineScope) {
    private val coroutineScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main)
    }

    private fun simpleFlow() = flow {
        for (i in 3 downTo 1 step 2) {
            emit(i)
        }
    }

    public fun getTestData(data: String, vm: FlowPracticeVM) {
        val flow = simpleFlow()
        coroutineScope.launch {
            flow.collect {
                withContext(Dispatchers.IO) {
                    delay(1000)
                }
                vm.getMutableLiveData().value = "$data$it/3"
                LoggerUtils.e("it = $it")
            }
        }
    }

    public fun getTestData2(data: String, vm: FlowPracticeVM) {
        coroutineScope.launch {
            (1..5).asFlow().filter {
                it % 2 == 0
            }.map {
                "map $it"
            }.collect {
                LoggerUtils.e("it = $it")
                delay(1000)
                vm.getMutableLiveData().value = "$data$it/5"
            }
        }
    }

    fun getTestData3(data: String, vm: FlowPracticeVM) {
        coroutineScope.launch {
            flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9).onEach {
                delay(1000)
            }.collect {
                LoggerUtils.e("it = $it")
                delay(1000)
                vm.getMutableLiveData().value = "$data$it/9"
            }
        }
    }

    fun getTestData4(data: String, vm: FlowPracticeVM) {
        viewModelScope.launch {
            (1..8).asFlow().onEach { delay(1000) }.collect {
                LoggerUtils.e("it = $it")
                vm.getMutableLiveData().value = "$data$it/8"
            }
        }
    }

}