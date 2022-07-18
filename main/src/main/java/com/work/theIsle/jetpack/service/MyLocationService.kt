package com.work.theIsle.jetpack.service

import androidx.lifecycle.LifecycleService
import com.work.theIsle.jetpack.observer.MyLocationObserver

/**
 * @Author TIKOU
 * @Date 2022/7/17-19:01
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class MyLocationService : LifecycleService() {
    private var myLocationObserver: MyLocationObserver = MyLocationObserver(this)

    init {
        lifecycle.addObserver(myLocationObserver)
    }

}