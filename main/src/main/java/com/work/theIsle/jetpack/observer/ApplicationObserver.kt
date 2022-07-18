package com.work.theIsle.jetpack.observer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.work.supportlib.LoggerUtils

/**
 * @Author TIKOU
 * @Date 2022/7/18-14:52
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 只和app有关和activity无关 ，onCreate 只会调用一次，onDestroy 不会调用
 */
class ApplicationObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public fun onCreate() {
        LoggerUtils.i("Lifecycle.Event.ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public fun onStart() {
        LoggerUtils.i("Lifecycle.Event.ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public fun onPause() {
        LoggerUtils.i("Lifecycle.Event.ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public fun onResume() {
        LoggerUtils.i("Lifecycle.Event.ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public fun onStop() {
        LoggerUtils.i("Lifecycle.Event.ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public fun onDestroy() {
        LoggerUtils.i("Lifecycle.Event.ON_DESTROY")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public fun onAny() {
        LoggerUtils.i("Lifecycle.Event.ON_ANY")
    }
}