package com.work.theIsle.app

import com.work.baselib.activity.BaseActivity
import com.work.theIsle.hilt.httpProcessor.IHttpProcessor

/**
 * @Author TIKOU
 * @Date 2022/7/16-19:47
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
abstract class BaseAppActivity : BaseActivity() {
    public fun getBaseHttp(): IHttpProcessor {
        return (application as BaseApp).baseHttp
    }
}