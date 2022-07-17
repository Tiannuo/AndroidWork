package com.work.theIsle.jetpack.ui

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @Author TIKOU
 * @Date 2022/7/17-17:43
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class MyChronometer @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) :
    Chronometer(context, attrs), LifecycleObserver {

    private var elapsedTime: Long = 0

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public fun startMeter() {
        this.base = SystemClock.elapsedRealtime() - elapsedTime
        this.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public fun stopMeter() {
        elapsedTime = SystemClock.elapsedRealtime() - this.base
        this.stop()
    }

}