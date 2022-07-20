package com.work.theIsle.jetpack.observable

import android.text.TextUtils
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.work.supportlib.LoggerUtils
import com.work.theIsle.BR
import com.work.theIsle.jetpack.bean.User

/**
 * @Author TIKOU
 * @Date 2022/7/20-16:21
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 实现双向绑定的第一种方式
 */
class UserObservable : BaseObservable() {
    private val user: User = User("Jack")

    @get:Bindable
    var userName: String?
        get() = user.name
        set(userName) {
            if (!TextUtils.isEmpty(userName) && !TextUtils.equals(userName, user.name)) {
                user.name = userName!!
                LoggerUtils.i(userName)
                notifyPropertyChanged(BR.userName)
            }
        }

}