package com.work.theIsle.jetpack.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author TIKOU
 * @Date 2022/7/19-14:43
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class LiveDataViewModel : ViewModel() {
    private var currentNumber: MutableLiveData<Int> = MutableLiveData<Int>()

    init {
        currentNumber.value = 0
    }

    fun getCurrentNumber(): MutableLiveData<Int> {
        return currentNumber
    }
}