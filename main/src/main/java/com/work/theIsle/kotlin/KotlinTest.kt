package com.work.theIsle.kotlin

import com.work.supportlib.LoggerUtils
import kotlin.contracts.contract

/**
 * @Author TIKOU
 * @Date 2022/7/22-13:50
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description kotlin学习
 */
class KotlinTest {
    fun test() {
        mapOf("jack" to 18, "jack" to 18)
    }

    fun mapTest() {
        val mutableMap = mutableMapOf("java" to 8)
        mutableMap.getOrPut("kotlin", getDefaultData())
        mutableMap.getOrPut("C++") { 9 }
        println(getDefaultData2())
        repeat(1000, { i -> LoggerUtils.e("i = $i") }, "张")
        repeat(1000, getIndexAction(), "王")
    }

    private fun getIndexAction(): (Int) -> Unit {
        return { }
    }


    // 函数作为返回类型,() -> Int 空参 ，返回值Int
    private fun getDefaultData(): () -> Int {
        return { 4 }
    }

    // 函数作为返回类型, (index: Int) -> Int ,lambda 最后一行作为闭包返回
    private fun getDefaultData2(): (index: Int) -> Int {
        return { index -> index }
    }

    private fun getDefaultData3(): (Int) -> Int {
        return { _ -> -1 }
    }

    private fun getDefaultData4(): (Int, name: String) -> Int {
        return { _, name ->
            println(name)
            -1
        }
    }

    // UNIT 相当于java void
    private fun getDefaultData5(): (Int, name: String) -> Unit {
        return { _, name ->
            println(name)
        }
    }

    private fun getDefaultData6(): (Int) -> Int {
        return { index -> index }
    }


    public inline fun repeat(times: Int, action: (Int) -> Unit, name: String) {

    }

}