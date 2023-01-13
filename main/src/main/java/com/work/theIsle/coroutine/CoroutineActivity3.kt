package com.work.theIsle.coroutine

import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_COROUTINE3
import com.work.theIsle.R
import com.work.theIsle.databinding.ActivityCoroutine3Binding
import com.work.theIsle.viewmodel.CoroutineViewModel

/**

 * @Author Administrator
 * @Date 2022/5/30-14:52
 * @Email wangweitikou1994@gmail.com
 * @Des 协程+dataBinding+viewBinding+liveData 小案例
 */
@Route(path = PATH_COROUTINE3)
class CoroutineActivity3 : BaseActivity() {

    private lateinit var binding: ActivityCoroutine3Binding
    private val coroutineViewModel: CoroutineViewModel by viewModels()

    override fun initView() {
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_coroutine3
        )
        binding.viewModel = coroutineViewModel
        binding.lifecycleOwner = this
        binding.btnCoroutine.setOnClickListener {
            coroutineViewModel.getUserData()
        }
        binding.btnFlow.setOnClickListener {
            coroutineViewModel.gotoFlowActivity()
        }
        binding.btnContext.setOnClickListener {
            coroutineViewModel.gotoContextActivity()
        }
        binding.btnScope.setOnClickListener {
            coroutineViewModel.gotoScopeActivity()
        }
        binding.btnLivedata.setOnClickListener {
            coroutineViewModel.gotoViewModelLiveData()
        }
        binding.btnException.setOnClickListener {
            coroutineViewModel.gotoExceptionHandler()
        }
        binding.btnFlowPractice.setOnClickListener {
            coroutineViewModel.gotoFlowPractice()
        }
        binding.btnFlowProject.setOnClickListener {
            coroutineViewModel.gotoFlowProject()
        }
    }

    override fun initData() {

    }

}