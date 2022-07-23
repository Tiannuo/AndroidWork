package com.work.theIsle.kotlin.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.work.login.bean.KotlinUserBean

/**
 * @Author TIKOU
 * @Date 2022/7/23-21:41
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class KotlinViewModel : ViewModel() {
    private var kotlinUserMutable: MutableLiveData<KotlinUserBean> = MutableLiveData()

    public fun setKotlinUserMutable(userMutable:MutableLiveData<KotlinUserBean>){
        this.kotlinUserMutable = userMutable
    }

    public fun getKotlinUserMutable() = kotlinUserMutable
}