package com.work.theIsle.kotlin

import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.work.baselib.arouter.RouterPath.PATH_KOTLINACTIVITY
import com.work.login.bean.KotlinUserBean
import com.work.theIsle.R
import com.work.theIsle.app.BaseAppActivity
import com.work.theIsle.databinding.ActivityKotlinBinding
import com.work.theIsle.kotlin.vm.KotlinViewModel

@Route(path = PATH_KOTLINACTIVITY)
class KotlinActivity : BaseAppActivity() {
    private lateinit var binding: ActivityKotlinBinding
    private val userModel:KotlinViewModel by viewModels()

    @Autowired(name = "key")
    lateinit var userBean: KotlinUserBean

    override fun initView() {
        ARouter.getInstance().inject(this)
        userModel.getKotlinUserMutable().value = userBean
        binding = DataBindingUtil.setContentView(this, R.layout.activity_kotlin)
        binding.lifecycleOwner = this
        binding.userModel = userModel
    }

    override fun initData() {

    }
}