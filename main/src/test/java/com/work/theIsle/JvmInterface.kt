package com.work.theIsle

/**
 * @Author TIKOU
 * @Date 2022/8/15-12:48
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
interface JvmInterface {
    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("testA")
    fun test(value: List<String>)


    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("testB")
    fun test(value: List<Int>)
}