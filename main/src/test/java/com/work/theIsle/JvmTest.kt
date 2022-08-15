package com.work.theIsle

/**
 * @Author TIKOU
 * @Date 2022/8/15-12:45
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class JvmTest {
    @JvmName("testA")
    fun test(value: List<String>) {}

    @JvmName("testB")
    fun test(value: List<Int>) {}
}