package com.work.theIsle.coroutine.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author TIKOU
 * @Date 2022/7/26-15:00
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class FlowPracticeVM : ViewModel() {
    private val mutableLiveData: MutableLiveData<Int> = MutableLiveData()

    init {
        mutableLiveData.value = 9527
    }

    fun getMutableLiveData() = mutableLiveData
}