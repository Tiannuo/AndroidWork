package com.work.theIsle.jetpack.activity

import android.view.View
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_JETPACKVIEWMODELACTIVITY
import com.work.theIsle.databinding.ActivityViewModelBinding
import com.work.theIsle.jetpack.vm.MyAndroidViewModel
import com.work.theIsle.jetpack.vm.MyViewModel

@Route(path = PATH_JETPACKVIEWMODELACTIVITY)
class ViewModelActivity : BaseActivity() {
    private val vm: MyViewModel by viewModels()

    private val vm_android:MyAndroidViewModel by viewModels()

    // private lateinit var vm: MyViewModel
    private lateinit var binding: ActivityViewModelBinding
    override fun initView() {
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*     vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                 MyViewModel::class.java
             )*/
    }

    override fun initData() {

    }

    fun plusNumber(view: View) {
        binding.tvNumber.text = vm.number++.toString()
        binding.tvNumberAndroid.text = vm_android.index++.toString()
        vm_android.show()
    }
}