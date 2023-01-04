package com.work.article

import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.article.databinding.ActivityKotlinindexBinding
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_KOTLININDEXACTIVITY

/**
 * @Author Administrator
 * @Date 2022/11/15-13:54
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@Route(path = PATH_KOTLININDEXACTIVITY)
class KotlinIndexActivity : BaseActivity() {
    private lateinit var binding:ActivityKotlinindexBinding

    override fun initView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_kotlinindex)
        binding.lifecycleOwner = this

    }

    override fun initData() {

    }
}