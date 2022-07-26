package com.work.theIsle.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.Logger
import com.work.baselib.arouter.RouterPath
import com.work.login.bean.QingHuaBean
import com.work.theIsle.coroutine.CoroutineActivity3
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

    fun gotoFlowActivity(context: Context) {
        ARouter.getInstance().build(RouterPath.PATH_FLOWACTIVITY).navigation(context)
    }

    fun gotoContextActivity(context: Context) {
        ARouter.getInstance().build(RouterPath.PATH_CONTEXTACTIVITY).navigation(context)
    }

    fun gotoScopeActivity(context: Context) {
        ARouter.getInstance().build(RouterPath.PATH_SCOPEACTIVITY).navigation(context)
    }

    fun gotoViewModelLiveData(context: Context) {
        ARouter.getInstance().build(RouterPath.PATH_VIEWMODELACTIVITY).navigation(context)
    }

    fun gotoExceptionHandler(context: Context) {
        ARouter.getInstance().build(RouterPath.PATH_EXCEOTIONHANDLERACTIVITY).navigation(context)
    }

    fun gotoFlowPractice(context: Context) {
        ARouter.getInstance().build(RouterPath.PATH_FLOWPRACTICEACTIVITY).navigation(context)
    }
}