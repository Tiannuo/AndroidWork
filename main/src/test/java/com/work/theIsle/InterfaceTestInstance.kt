package com.work.theIsle

/**
 * @Author TIKOU
 * @Date 2022/8/14-19:15
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class InterfaceTestInstance(override val name: String = "default") : InterfaceTest {
    override var age: Int
        get() = super.age
        set(value) {

        }
}