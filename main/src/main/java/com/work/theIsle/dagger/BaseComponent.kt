package com.work.theIsle.dagger

import android.app.Activity

/**
 * @Author TIKOU
 * @Date 2022/7/9-20:10
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
interface BaseComponent<T:Activity> {
    public fun injectActivity(activity: T)
}