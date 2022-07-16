package com.work.theIsle.hilt

import javax.inject.Inject

/**
 * @Author TIKOU
 * @Date 2022/7/15-18:37
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class TestDAATA @Inject constructor(private val name: String, private val index: Int) {
    constructor() : this("", 0) {}
}