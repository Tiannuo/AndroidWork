package com.work.theIsle.coroutine.projectdemo

import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseDataBindingViewModelActivity
import com.work.baselib.arouter.RouterPath.PATH_COROUTINE_PROJECT_DEMO
import com.work.theIsle.R
import com.work.theIsle.coroutine.vm.ProjectDemoVM
import com.work.theIsle.databinding.ActivityProjectDemoBinding

/**
 * @Author Admini
 * @Date 2023/1/15-15:56
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description https://www.bilibili.com/video/BV1uo4y1y7ZF?p=118&vd_source=417038b5e38bdfdbfc74f04adfaf8d03
 */
@Route(path = PATH_COROUTINE_PROJECT_DEMO)
class ProjectDemoActivity :
    BaseDataBindingViewModelActivity<ActivityProjectDemoBinding, ProjectDemoVM>() {

    override fun getLayout(): Int = R.layout.activity_project_demo

    override fun bindingVM(binding: ActivityProjectDemoBinding, vm: ProjectDemoVM) {
        binding.vm = vm
    }

    override fun initView() {

    }

    override fun initData() {

    }

}