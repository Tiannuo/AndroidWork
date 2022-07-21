package com.work.theIsle.jetpack.activity

import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_DBVMLDACTIVITY
import com.work.theIsle.R
import com.work.theIsle.databinding.ActivityDbvmldactivityBinding
import com.work.theIsle.jetpack.lis.DBVMLDListener
import com.work.theIsle.jetpack.vm.DBVMLDVM

/**
 * @Author TIKOU
 * @Date 2022/7/21-14:56
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description DataBinding+ViewModel+LiveData结合使用案例
 */
@Route(path = PATH_DBVMLDACTIVITY)
class DBVMLDActivity : BaseActivity() {
    private lateinit var binding: ActivityDbvmldactivityBinding
    private val scoreModel: DBVMLDVM by viewModels()
    override fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dbvmldactivity)
        // dataBinding 持久化数据的精髓
        binding.lifecycleOwner = this
        binding.scoreModel = scoreModel
        binding.event = DBVMLDListener()
    }

    override fun initData() {

    }

}