package com.work.theIsle.jetpack.lis

import android.view.View
import com.work.supportlib.LoggerUtils
import com.work.theIsle.jetpack.vm.DBVMLDVM

/**
 * @Author TIKOU
 * @Date 2022/7/21-15:31
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description DBVMLD 界面点击事件
 */
class DBVMLDListener {
    /**
     * 加分
     * @param v View
     * @param vm DBVMLDVM
     * @param teamtype TEAMTYPE
     * @param score Int
     */
    fun addScore(v: View, vm: DBVMLDVM, teamtype: TEAMTYPE, score: Int) {
        vm.saveLastTeamScore()
        if (teamtype == TEAMTYPE.A) {
            vm.getATeamSore().value = vm.getATeamSore().value?.plus(score)
        } else {
            vm.getBTeamSore().value = vm.getBTeamSore().value?.plus(score)
        }
    }

    /**
     * 撤销
     * @param v View
     */
    fun back(v: View, vm: DBVMLDVM) {
        LoggerUtils.i("back aLastTeamSore = ${vm.aLastTeamSore} bLastTeamSore = ${vm.bLastTeamSore}")
        vm.getATeamSore().value = vm.aLastTeamSore
        vm.getBTeamSore().value = vm.bLastTeamSore
    }

    /**
     * 重置
     * @param v View
     * @param vm DBVMLDVM
     */
    fun reSet(v: View, vm: DBVMLDVM) {
        vm.getATeamSore().value = 0
        vm.getBTeamSore().value = 0
    }

    enum class TEAMTYPE {
        A, B
    }
}