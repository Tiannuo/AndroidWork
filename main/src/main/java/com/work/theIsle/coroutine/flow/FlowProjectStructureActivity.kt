package com.work.theIsle.coroutine.flow

import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseDataBindingViewModelActivity
import com.work.baselib.arouter.RouterPath.PATH_FLOWPROJECTSTRUCTUREACTIVITY
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import com.work.theIsle.coroutine.vm.FlowProjectStructureVM
import com.work.theIsle.databinding.ActivityFlowProjectStructureBinding

/**
 * @Author Admini
 * @Date 2023/1/13-13:31
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@Route(path = PATH_FLOWPROJECTSTRUCTUREACTIVITY)
class FlowProjectStructureActivity :
    BaseDataBindingViewModelActivity<ActivityFlowProjectStructureBinding, FlowProjectStructureVM>() {

    override fun initView() {
        getBinding().event = FlowProjectStructureListener()
    }

    override fun initData() {
        LoggerUtils.e("initData")
    }

    override fun getLayout(): Int {
        return R.layout.activity_flow_project_structure
    }

    override fun bindingVM(
        binding: ActivityFlowProjectStructureBinding, vm: FlowProjectStructureVM
    ) {
        binding.vm = vm
    }
}