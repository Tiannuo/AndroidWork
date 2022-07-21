package com.work.theIsle.jetpack.activity

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_RecyclerViewACTIVITY
import com.work.theIsle.R
import com.work.theIsle.databinding.ActivityRecyclerViewBinding
import com.work.theIsle.jetpack.adapter.ItemAvatarAdapter

/**
 * @Author TIKOU
 * @Date 2022/7/21-14:17
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@Route(path = PATH_RecyclerViewACTIVITY)
class RecyclerViewActivity : BaseActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var rvAdapter: ItemAvatarAdapter
    override fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view)
        rvAdapter = ItemAvatarAdapter()
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter =rvAdapter

    }

    override fun initData() {
        rvAdapter.initData()
    }


}