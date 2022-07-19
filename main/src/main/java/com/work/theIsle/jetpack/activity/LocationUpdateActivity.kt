package com.work.theIsle.jetpack.activity

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_lOCATIONUPDATEACTIVITY
import com.work.theIsle.databinding.ActivityLocationUpdateBinding

import com.work.theIsle.jetpack.service.MyLocationService

/**
 * @Author TIKOU
 * @Date 2022/7/18-14:48
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description LifecycleService 解耦Service组件案例
 */
@Route(path = PATH_lOCATIONUPDATEACTIVITY)
class LocationUpdateActivity : BaseActivity() {
    private lateinit var binding: ActivityLocationUpdateBinding;
    override fun initView() {
        binding = ActivityLocationUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            startService(Intent(this@LocationUpdateActivity, MyLocationService::class.java))
        }

        binding.btnStop.setOnClickListener {
            stopService(Intent(this@LocationUpdateActivity, MyLocationService::class.java))
        }
    }

    override fun initData() {

    }

}