package com.work.supportlib

import android.content.Context

/**
 * @time 2018/6/27 16:57
 * 作者：wangwei
 * 邮箱：ww@winning.com.cn
 * 说明：屏幕转换关系（获取参数值）
 * 参考：
 */
object ScreenUtil {
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun isListEmpty(list: List<*>?): Boolean {
        return list == null || list.size == 0
    }
}