package com.work.theIsle.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.login.bean.QingHuaBean
import com.work.theIsle.repository.QinghuaRepository
import kotlinx.coroutines.launch

/**
 * @Author TIKOU
 * @Date 2022/7/6-17:54
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description CoroutineViewModelActivity的viewModel
 */
class CoroutineViewModelLiveData : ViewModel() {
    val qingHuaBeanLiveData = MutableLiveData<QingHuaBean>()
    private val qinghuaRepository = QinghuaRepository()
    fun getQingHuaData() {
        viewModelScope.launch {
            qingHuaBeanLiveData.value = qinghuaRepository.getQingHuaData()
        }
    }
}