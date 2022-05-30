package com.work.theIsle.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.work.login.bean.QingHuaBean
import com.work.theIsle.repository.UserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**

 * @Author Administrator
 * @Date 2022/5/30-15:05
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
class CoroutineViewModel : ViewModel() {
    val userDataLiveData = MutableLiveData<QingHuaBean>()
    private val userDataRepository = UserDataRepository()

    fun getUserData() {
        viewModelScope.launch() {
            userDataLiveData.value = userDataRepository.getUserData()
        }
    }
}