package com.work.theIsle

import com.work.supportlib.LoggerUtils
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * @Author TIKOU
 * @Date 2022/7/8-13:21
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 全局异常处理器
 */
class GlobalCoroutineExceptionHandler : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*>
        get() = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        LoggerUtils.i("handleException${context} ${exception.message}")
    }
}