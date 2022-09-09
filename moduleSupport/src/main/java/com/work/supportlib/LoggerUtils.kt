package com.work.supportlib

import com.orhanobut.logger.Logger

/**
 * @Author TIKOU
 * @Date 2022/7/4-19:01
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 打印数据管理工具
 */
object LoggerUtils {
    fun i(message: String, vararg args: Any) {
        baseLogger(LoggerType.I, message, args)
    }

    fun i(message: Any?, vararg args: Any) {
        if (message != null) {
            baseLogger(LoggerType.I, message.toString(), args)
        }
    }

    fun i(message: Int, vararg args: Any) {
        baseLogger(LoggerType.I, message.toString(), args)
    }

    fun e(message: String, vararg args: Any) {
        baseLogger(LoggerType.E, message, args)
    }

    private fun baseLogger(type: LoggerType, message: String, vararg args: Any) {
        if (BuildConfig.DEBUG) {
            when (type) {
                LoggerType.I -> Logger.i(message, args)
                LoggerType.D -> Logger.d(message, args)
                LoggerType.E -> Logger.e(message, args)
            }
        }
    }

    enum class LoggerType {
        I, D, E
    }
}

