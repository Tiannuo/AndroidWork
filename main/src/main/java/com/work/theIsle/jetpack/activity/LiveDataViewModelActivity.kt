package com.work.theIsle.jetpack.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_LIVEDATAVIEWMODELACTIVITY
import com.work.theIsle.R

/**
 * @Author TIKOU
 * @Date 2022/7/20-10:32
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description  实现liveData+viewModel 在 Fragment中通讯的案例
 */
@Route(path = PATH_LIVEDATAVIEWMODELACTIVITY)
class LiveDataViewModelActivity : BaseActivity() {

    override fun initView() {
        setContentView(R.layout.activity_live_data_view_model)
    }

    override fun initData() {

    }

}