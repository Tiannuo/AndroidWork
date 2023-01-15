package com.work.theIsle.coroutine

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.work.theIsle.viewmodel.CoroutineViewModel

/**
 * @Author Admini
 * @Date 2023/1/15-16:30
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class CoroutineClickListener {
    fun gotoStepActivity(v: View, routePath: String, vm: CoroutineViewModel) {
        when (routePath) {
            else -> ARouter.getInstance().build(routePath).navigation(v.context)
        }

    }

    fun getUserData(v: View, vm: CoroutineViewModel) {
        vm.getUserData()
    }
}