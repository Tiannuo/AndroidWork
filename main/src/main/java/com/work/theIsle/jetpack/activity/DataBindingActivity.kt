package com.work.theIsle.jetpack.activity


import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_DATABINDINGACTIVITY
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import com.work.theIsle.databinding.ActivityDataBindingBinding
import com.work.theIsle.jetpack.bean.Idol
import com.work.theIsle.jetpack.observable.UserObservable
import com.work.theIsle.jetpack.observable.UserObservableFiled
import com.work.theIsle.jetpack.observable.UserObservableForJava


@Route(path = PATH_DATABINDINGACTIVITY)
class DataBindingActivity : BaseActivity() {
    private lateinit var binding: ActivityDataBindingBinding
    private lateinit var idol: Idol
    override fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
    }

    override fun initData() {
        idol = Idol("Android", "Jetpack")
        binding.idol = idol
        binding.netWorkImage =
            "https://c-ssl.duitang.com/uploads/blog/202107/09/20210709142454_dc8dc.jpeg"
        binding.localImage = R.mipmap.ic_launcher
        binding.userObservable = UserObservable()
        binding.userObservableFiled = UserObservableFiled()
        binding.userObservableForJava = UserObservableForJava()
    }

    override fun onResume() {
        super.onResume()
        //dataBinding 默认单向绑定，修改idol的数值，对应的布局控件数据也会发生改变
        idol.name = "onResume"
        //布局控件数据发生改变,idol的数值却不会发生改变
        // binding.tvDec.text = "onResume"
        //binding.includeInfo.tvName
        LoggerUtils.i(binding.idol!!.des) //Jetpack
    }

}