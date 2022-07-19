package com.work.theIsle.jetpack.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.work.supportlib.LoggerUtils
import com.work.theIsle.app.BaseApp


/**
 * @Author TIKOU
 * @Date 2022/7/19-14:15
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class MyAndroidViewModel(application: Application) : AndroidViewModel(application) {
    var index: Int = 0

    fun show() {
        LoggerUtils.i("application ${getApplication<BaseApp>().hashCode()}")
    }
}