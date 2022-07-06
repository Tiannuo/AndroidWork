package com.work.theIsle.coroutine

import android.text.TextUtils
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_VIEWMODELACTIVITY
import com.work.theIsle.R
import com.work.theIsle.databinding.ActivityCoroutineViewModelBinding
import com.work.theIsle.viewmodel.CoroutineViewModelLiveData

/**
 * @Author TIKOU
 * @Date 2022/7/6-18:12
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 结合viewModel LiveData viewBinding viewModelScope 实现
 */
@Route(path = PATH_VIEWMODELACTIVITY)
class CoroutineViewModelActivity : BaseActivity() {
    private var binding: ActivityCoroutineViewModelBinding? = null
    private val viewModel: CoroutineViewModelLiveData by viewModels()
    override fun initView() {
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_coroutine_view_model
        )
        binding!!.viewModel = viewModel
        binding!!.lifecycleOwner = this
        binding!!.btnTest.setOnClickListener {
            viewModel.getQingHuaData()
        }
    }

    override fun initData() {

    }
}