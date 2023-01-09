package com.work.theIsle.coroutine.flow

import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseDataBindingViewModelActivity
import com.work.baselib.arouter.RouterPath.PATH_FLOWPRACTICEACTIVITY
import com.work.theIsle.R
import com.work.theIsle.coroutine.vm.FlowPracticeVM
import com.work.theIsle.databinding.ActivityFlowPracticeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

@Route(path = PATH_FLOWPRACTICEACTIVITY)
class FlowPracticeActivity :
    BaseDataBindingViewModelActivity<ActivityFlowPracticeBinding, FlowPracticeVM>(),
    CoroutineScope by MainScope() {

    override fun initView() {
        getBinding().event = FlowPracticeListener()
    }

    override fun initData() {

    }

    override fun getLayout(): Int = R.layout.activity_flow_practice

    override fun bindingVM(binding: ActivityFlowPracticeBinding, vm: FlowPracticeVM) {
        binding.vm = vm
    }
}