package com.work.article

/**
 * @Author Admini
 * @Date 2023/1/5-18:02
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class IndexRepository {
    suspend fun getString(name: String): IndexUser {
        return IndexUser(name)
    }
}