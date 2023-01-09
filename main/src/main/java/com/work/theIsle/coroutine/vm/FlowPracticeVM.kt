package com.work.theIsle.coroutine.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.supportlib.LoggerUtils
import com.work.theIsle.coroutine.flow.FlowRepository
import com.work.theIsle.coroutine.flow.FlowType

/**
 * @Author TIKOU
 * @Date 2022/7/26-15:00
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class FlowPracticeVM : ViewModel() {
    private val mutableLiveData: MutableLiveData<String> = MutableLiveData()
    private val flowRepository: FlowRepository by lazy {
        FlowRepository(viewModelScope)
    }

    init {
        mutableLiveData.value = "9527"
    }

    fun getMutableLiveData() = mutableLiveData

    fun getData(data: String, vm: FlowPracticeVM, type: FlowType) {
        when (type) {
            FlowType.ONE -> flowRepository.getTestData(data, vm)
            FlowType.TWO -> flowRepository.getTestData2(data, vm)
            FlowType.THREE -> flowRepository.getTestData3(data, vm)
            FlowType.FOUR->flowRepository.getTestData4(data,vm)
            else -> LoggerUtils.e("type value not found!")
        }

    }
}