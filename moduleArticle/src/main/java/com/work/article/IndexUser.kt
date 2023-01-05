package com.work.article

/**
 * @Author Admini
 * @Date 2023/1/5-18:34
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
data class IndexUser(val name: String) {

    public fun userDesc(): String {
        return "玩家信息 name = $name"
    }
}