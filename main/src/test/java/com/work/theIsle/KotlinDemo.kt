package com.work.theIsle

import org.junit.Test

/**
 * @Author TIKOU
 * @Date 2022/8/13-17:49
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class KotlinDemo {

    @Test
    fun mapTest() {
        val mutableMap = mutableMapOf("java" to 8)
        mutableMap.getOrPut("kotlin", getDefaultData())
        mutableMap.getOrPut("C++") { 9 }
        mutableMap.getOrPut("US") { 22 }

        val cinfig = getDefaultData2()
        cinfig(3)

    }

    // 函数作为返回类型
    private fun getDefaultData(): () -> Int {
        return { 4 }
    }



    private fun getDefaultData2(): (index: Int) -> Int {
        return { index -> index }
    }
}