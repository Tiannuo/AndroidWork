package com.work.theIsle.repository

import com.work.httplib.httputils.HttpUtils
import com.work.login.bean.QingHuaBean
import com.work.theIsle.api.UmogApi

/**
 * @Author TIKOU
 * @Date 2022/7/6-18:04
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 资源代理类，用于请求viewModel需要的网络请求，数据库资源等
 */
class QinghuaRepository {
    suspend fun getQingHuaData(): QingHuaBean {
        return HttpUtils.createApi(UmogApi::class.java).loadQing()
    }
}