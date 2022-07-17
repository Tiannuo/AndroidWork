package com.work.theIsle.jetpack

import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.arouter.RouterPath.PATH_JETPACKACTIVITY
import com.work.theIsle.R
import com.work.theIsle.app.BaseAppActivity
import com.work.theIsle.jetpack.ui.MyChronometer

@Route(path = PATH_JETPACKACTIVITY)
class JetpackActivity : BaseAppActivity() {
    private lateinit var chronometer: MyChronometer

    override fun initView() {
        setContentView(R.layout.activity_jetpack)
        chronometer = findViewById(R.id.chronometer)
        // MyChronometer 实现 LifecycleObserver
        // lifecycle 增加观察者 MyChronometer
        lifecycle.addObserver(chronometer)
    }

    override fun initData() {

    }
}