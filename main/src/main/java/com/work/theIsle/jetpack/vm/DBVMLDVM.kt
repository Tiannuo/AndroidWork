package com.work.theIsle.jetpack.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Author TIKOU
 * @Date 2022/7/21-15:44
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class DBVMLDVM : ViewModel() {
    private var aTeamSore: MutableLiveData<Int>? = null
    private var bTeamSore: MutableLiveData<Int>? = null
    var aLastTeamSore: Int? = 0
    var bLastTeamSore: Int? = 0
    fun getATeamSore(): MutableLiveData<Int> {
        if (aTeamSore == null) {
            aTeamSore = MutableLiveData()
            aTeamSore!!.value = 0
        }
        return aTeamSore!!
    }

    fun setATeamSore(aaa: MutableLiveData<Int>?) {
        this.aTeamSore = aaa
    }

    fun getBTeamSore(): MutableLiveData<Int> {
        if (bTeamSore == null) {
            bTeamSore = MutableLiveData()
            bTeamSore!!.value = 0
        }
        return bTeamSore!!
    }

    fun setBTeamSore(bbb: MutableLiveData<Int>?) {
        this.bTeamSore = bbb
    }

    fun saveLastTeamScore() {
        aLastTeamSore = aTeamSore?.value
        bLastTeamSore = bTeamSore?.value
    }
}