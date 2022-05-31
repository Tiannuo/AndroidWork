package com.work.httplib.bean

import com.work.httplib.handle.HttpErrorDetail
import com.google.gson.annotations.SerializedName

/**
 * Created by zhaojian on 2018/2/5.
 */
class BaseResultBean<T> {
    @JvmField
    var code = 0
    @JvmField
    var message: String? = null
    @JvmField
    var errorDetail: HttpErrorDetail? = null

    @JvmField
    @SerializedName("result")
    var data //数据
            : T? = null
    var logId: String? = null
}