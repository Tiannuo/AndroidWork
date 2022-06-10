package com.work.theIsle

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**

 * @Author Administrator
 * @Date 2022/6/8-14:28
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
class CoroutineTestFlow {
    fun simpleList(): List<Int> = listOf<Int>(1, 2, 3)
    fun simpleSequence(): Sequence<Int> = sequence {
        for (i in 1..3) {
            Thread.sleep(1000)
            yield(i)
        }
    }

    @Test
    fun testMultipleValues() {
        simpleSequence().forEach {
            println("it = $it")
        }
    }

    @Test
    fun testMultipleValues2() = runBlocking {
        simpleList2().forEach {
            println("it = $it")
        }
    }


    suspend fun simpleList2(): List<Int> {
        delay(1000)
        return listOf(1, 2, 3)
    }

    suspend fun simpleFlow() = flow<Int> {
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }


    @Test
    fun testMultipleValues3() = runBlocking {

        launch {
            for (k in 1..3){
                println("runBlocking ")
                delay(1000)
            }

        }
        simpleFlow().collect {
            println("it = $it")
        }
    }


}