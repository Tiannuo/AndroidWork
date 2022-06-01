package com.work.httplib.handle

import com.work.httplib.handle.HttpErrorDetail
import com.work.httplib.bean.BaseResultBean
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by zhaojian on 2018/2/5.
 * http消息异常
 */
class ApiExceptionBy(
    @field:Type @get:Type
    @param:Type val type: Int,
    val requestError: HttpErrorDetail,
    val errorCode: Int,
    val baseResultBean: BaseResultBean<*>
) : Throwable(
    requestError.errorMsg
) {
    override fun toString(): String {
        return "{type=" + type +
                ", requestError=" + requestError +
                '}'
    }

    @Retention(RetentionPolicy.SOURCE)
    internal annotation class Type
    companion object {
        const val TYPE_HTTP = 1 // 网络异常
        const val TYPE_SERVER_DATA = 2 // 服务端数据异常
    }
}