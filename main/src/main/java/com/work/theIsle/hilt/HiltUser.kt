package com.work.theIsle.hilt

import javax.inject.Inject

/**
 * @Author TIKOU
 * @Date 2022/7/15-18:01
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class HiltUser(val name: String) {
    var index: Int = 0

    @Inject
    constructor() : this("123")

}