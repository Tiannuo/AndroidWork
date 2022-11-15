package com.work.theIsle.openSourceFramework.glide

import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_GLIDEACTIVITY
import com.work.theIsle.R
import com.work.theIsle.constant.Constant.IMG_URL
import com.work.theIsle.databinding.ActivityGlideBinding

@Route(path = PATH_GLIDEACTIVITY)
class GlideActivity : BaseActivity() {

    private lateinit var binding: ActivityGlideBinding
    private val glideVM: GlideViewModel by viewModels()

    override fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_glide)
        binding.lifecycleOwner = this
        binding.glideVm = glideVM

        binding.btnTest.setOnClickListener {
            binding.imgUrl = IMG_URL
            glideVM.notifyImgData()
        }
    }

    override fun initData() {

    }

}