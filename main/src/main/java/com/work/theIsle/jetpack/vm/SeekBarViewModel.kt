package com.work.theIsle.jetpack.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * @Author TIKOU
 * @Date 2022/7/20-10:56
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class SeekBarViewModel(application: Application) : AndroidViewModel(application) {
    private var progress: MutableLiveData<Int> = MutableLiveData()

    init {
        progress.value = 0
    }

    public fun getProgress(): MutableLiveData<Int> {
        return progress
    }
}