package com.work.theIsle.hilt.interfacedi

import com.work.supportlib.LoggerUtils
import javax.inject.Inject

/**
 * @Author TIKOU
 * @Date 2022/7/15-16:40
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class TestClass @Inject constructor() : TestInterface {
    override fun method() {
        LoggerUtils.i("TestClass method")
    }
}