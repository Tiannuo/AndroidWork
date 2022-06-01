package com.work.httplib.dn.kt

import android.text.TextUtils

/**
 * 具体的返回的类型数据不知道：
 * 通过接口的形式来转换兼容
 * @param <T>
</T> */
open class KTBaseResponse<T>(
    override val content: T,
    override val msg: String,
    override val code: String
) : KTIResponse<T> {

    override val isSuccess: Boolean
        get() = TextUtils.equals("1", code)
}