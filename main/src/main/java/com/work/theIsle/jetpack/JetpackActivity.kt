package com.work.theIsle.jetpack

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.work.baselib.arouter.RouterPath
import com.work.baselib.arouter.RouterPath.PATH_JETPACKACTIVITY
import com.work.theIsle.app.BaseAppActivity
import com.work.theIsle.databinding.ActivityJetpackBinding
import com.work.theIsle.jetpack.ui.MyChronometer

@Route(path = PATH_JETPACKACTIVITY)
class JetpackActivity : BaseAppActivity() {
    private lateinit var chronometer: MyChronometer
    private lateinit var binding: ActivityJetpackBinding
    override fun initView() {
        binding = ActivityJetpackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        chronometer = binding.chronometer
        // MyChronometer 实现 LifecycleObserver
        // lifecycle 增加观察者 MyChronometer
        lifecycle.addObserver(chronometer)
        binding.btnLocation.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PATH_lOCATIONUPDATEACTIVITY).navigation()
        }
    }

    override fun initData() {

    }
}