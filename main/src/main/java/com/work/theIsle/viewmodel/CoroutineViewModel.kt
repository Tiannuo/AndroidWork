package com.work.theIsle.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
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
 * @Des 不可以使用activity作为context的引用，会造成内存泄露，所以使用AndroidViewModel
 */
class CoroutineViewModel(application: Application) : AndroidViewModel(application) {
    val userDataLiveData = MutableLiveData<QingHuaBean>()
    private val userDataRepository = UserDataRepository()

    fun getUserData() {
        viewModelScope.launch() {
            userDataLiveData.value = userDataRepository.getUserData()
        }
    }

    fun gotoFlowActivity() {
        ARouter.getInstance().build(RouterPath.PATH_FLOWACTIVITY).navigation(getApplication())
    }

    fun gotoContextActivity() {
        ARouter.getInstance().build(RouterPath.PATH_CONTEXTACTIVITY).navigation(getApplication())
    }

    fun gotoScopeActivity() {
        ARouter.getInstance().build(RouterPath.PATH_SCOPEACTIVITY).navigation(getApplication())
    }

    fun gotoViewModelLiveData() {
        ARouter.getInstance().build(RouterPath.PATH_VIEWMODELACTIVITY).navigation(getApplication())
    }

    fun gotoExceptionHandler() {
        ARouter.getInstance().build(RouterPath.PATH_EXCEOTIONHANDLERACTIVITY).navigation(getApplication())
    }

    fun gotoFlowPractice() {
        ARouter.getInstance().build(RouterPath.PATH_FLOWPRACTICEACTIVITY).navigation(getApplication())
    }

    fun gotoFlowProject() {
        ARouter.getInstance().build(RouterPath.PATH_FLOWPROJECTSTRUCTUREACTIVITY).navigation(getApplication())
    }
}