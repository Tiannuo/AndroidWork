package com.work.theIsle.jetpack.lis

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.work.baselib.arouter.RouterPath
import com.work.supportlib.LoggerUtils

/**
 * @Author TIKOU
 * @Date 2022/7/19-15:02
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description Jetpack 跳转的所有控件监听
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

    public fun gotoDataBinding(v: View) {
        LoggerUtils.i("JetpackListener gotoDataBinding")
        ARouter.getInstance().build(RouterPath.PATH_DATABINDINGACTIVITY).navigation()
    }

    public fun gotoRecyclerView(v: View) {
        LoggerUtils.i("JetpackListener gotoRecyclerView")
        ARouter.getInstance().build(RouterPath.PATH_RecyclerViewACTIVITY).navigation()
    }

    public fun gotoDBVMLD(v: View) {
        LoggerUtils.i("JetpackListener DBVMLD")
        ARouter.getInstance().build(RouterPath.PATH_DBVMLDACTIVITY).navigation()
    }
}