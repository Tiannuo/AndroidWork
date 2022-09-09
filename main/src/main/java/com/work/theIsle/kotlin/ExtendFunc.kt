package com.work.theIsle.kotlin

import com.work.supportlib.LoggerUtils

/**
 * @Author TIKOU
 * @Date 2022/8/15-14:52
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */

fun String.addExt(amount: Int = 1): String {
    return this.plus("!".repeat(amount))
}

fun Any.printlnLog() = LoggerUtils.i(this)

