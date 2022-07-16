package com.work.theIsle.hilt

import javax.inject.Inject

/**
 * @Author TIKOU
 * @Date 2022/7/15-18:24
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class TestObject @Inject constructor(val name: String) {
    constructor() : this("11")
}