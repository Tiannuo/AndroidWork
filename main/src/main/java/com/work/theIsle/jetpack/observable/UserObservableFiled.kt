package com.work.theIsle.jetpack.observable

import androidx.databinding.ObservableField
import com.work.supportlib.LoggerUtils
import com.work.theIsle.jetpack.bean.User

/**
 * @Author TIKOU
 * @Date 2022/7/20-18:16
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 实现双向绑定的第二种方式（更简洁）
 */
class UserObservableFiled {
    private val userObservableFiled: ObservableField<User> = ObservableField()

    init {
        userObservableFiled.set(User("Rose"))
    }

    var userName: String?
        get() = userObservableFiled.get()!!.name
        set(name) {
            userObservableFiled.get()!!.name = name!!
            LoggerUtils.i("UserObservableFiled $name")
        }
}