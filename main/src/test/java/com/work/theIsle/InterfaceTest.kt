package com.work.theIsle

/**
 * @Author TIKOU
 * @Date 2022/8/14-19:07
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
interface InterfaceTest {
    val name: String
    val age: Int
        get() = (1..20).shuffled().first()
}