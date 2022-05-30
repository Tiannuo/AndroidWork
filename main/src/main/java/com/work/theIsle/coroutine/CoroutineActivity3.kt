package com.work.theIsle.coroutine

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
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
class CoroutineActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutine3Binding
    private val coroutineViewModel:CoroutineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityCoroutine3Binding>(
            this,
            R.layout.activity_coroutine3
        )
        binding.viewModel = coroutineViewModel
        binding.lifecycleOwner = this
        binding.btnCoroutine.setOnClickListener {
            coroutineViewModel.getUserData()
        }
    }

}