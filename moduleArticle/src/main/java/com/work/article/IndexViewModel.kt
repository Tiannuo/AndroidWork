package com.work.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @Author Admini
 * @Date 2023/1/5-17:58
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class IndexViewModel : ViewModel() {
    public val userLiveData = MutableLiveData<IndexUser>()
    private val userRepository: IndexRepository by lazy { IndexRepository() }

    init {
        userLiveData.value = IndexUser("index")
    }

    fun getUser(name: String) {
        viewModelScope.launch {
            userLiveData.value = userRepository.getString(name)
        }
    }
}