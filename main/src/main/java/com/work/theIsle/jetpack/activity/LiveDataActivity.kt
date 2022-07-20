package com.work.theIsle.jetpack.activity

import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_LIVEDATAACTIVITY

import com.work.supportlib.LoggerUtils
import com.work.theIsle.databinding.ActivityLiveDataBinding
import com.work.theIsle.jetpack.vm.LiveDataViewModel
import java.util.*

/**
 * @Author TIKOU
 * @Date 2022/7/19-14:39
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description liveData实现数据动态绑定
 */
@Route(path = PATH_LIVEDATAACTIVITY)
class LiveDataActivity : BaseActivity() {
    private lateinit var vm: LiveDataViewModel
    private var timer: Timer? = Timer()
    private var timeTask: TimerTask? = null
    private lateinit var binding: ActivityLiveDataBinding
    private var isRun: Boolean = false
    override fun initView() {
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            startClock()
        }
        binding.btnStop.setOnClickListener {
            stopClock()
        }

        binding.btnPause.setOnClickListener {
            pauseClock()
        }
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
            LiveDataViewModel::class.java
        )
        binding.tvNumber.text = vm.getCurrentNumber().value.toString()
        vm.getCurrentNumber().observe(this) {
            binding.tvNumber.text = it.toString()
        }
        LoggerUtils.i("LiveDataActivity initView")

    }

    override fun initData() {
    }

    override fun onPause() {
        super.onPause()
        LoggerUtils.i("LiveDataActivity onPause")
    }

    override fun onResume() {
        super.onResume()
        LoggerUtils.i("LiveDataActivity onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        LoggerUtils.i("LiveDataActivity onDestroy")
    }

    internal inner class MyTimeTask() : TimerTask() {
        override fun run() {
            vm.getCurrentNumber().postValue(vm.getCurrentNumber().value?.plus(1) ?: -1)
        }

    }

    private fun startClock() {

        if (timer == null) {
            timer = Timer()
        }
        if (timeTask == null) {
            timeTask = MyTimeTask()
        }
        if (timeTask != null && timer != null) {
            timer?.schedule(timeTask, 1000, 1000)
        }
    }

    private fun stopClock() {
        pauseClock()
        vm.getCurrentNumber().value = 0
    }

    private fun pauseClock() {
        if (timer != null) {
            timer!!.cancel()
            // 不加这句会报错Task already scheduled or cancelled
            timer!!.purge()
            timer = null
        }
        if (timeTask != null) {
            timeTask!!.cancel()
            timeTask = null
        }
    }
}