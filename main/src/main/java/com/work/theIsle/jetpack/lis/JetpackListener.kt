package com.work.theIsle.jetpack.lis

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.work.baselib.arouter.RouterPath
import com.work.supportlib.LoggerUtils

/**
 * @Author TIKOU
 * @Date 2022/7/19-15:02
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class JetpackListener {

    public fun gotoLocationUpdates(v: View, index: Int = 0) {
        LoggerUtils.i("JetpackListener gotoLiveData index@${index}")
        ARouter.getInstance().build(RouterPath.PATH_lOCATIONUPDATEACTIVITY).navigation()
    }

    public fun gotoViewModel(v: View) {
        LoggerUtils.i("JetpackListener gotoViewModel")
        ARouter.getInstance().build(RouterPath.PATH_JETPACKVIEWMODELACTIVITY).navigation()
    }

    public fun gotoLiveData(index: Int = 0) {
        LoggerUtils.i("JetpackListener gotoLiveData index@${index}")
        ARouter.getInstance().build(RouterPath.PATH_LIVEDATAACTIVITY).navigation()
    }

    public fun gotoLiveDataVM(v: View) {
        LoggerUtils.i("JetpackListener gotoLiveDataVM")
        ARouter.getInstance().build(RouterPath.PATH_LIVEDATAVIEWMODELACTIVITY).navigation()
    }
}