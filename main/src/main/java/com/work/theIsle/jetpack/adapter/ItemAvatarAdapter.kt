package com.work.theIsle.jetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.work.theIsle.R
import com.work.theIsle.databinding.ItemAvastarBinding
import com.work.theIsle.jetpack.bean.ItemAvatarBean
import com.work.theIsle.jetpack.bean.ItemAvatarBeanUtils

/**
 * @Author TIKOU
 * @Date 2022/7/21-13:56
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class ItemAvatarAdapter : RecyclerView.Adapter<ItemAvatarAdapter.ItemAvatarVH>() {
    private var avatarList = mutableListOf<ItemAvatarBean>()

    class ItemAvatarVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemBinding: ItemAvastarBinding? = null

        constructor(binding: ItemAvastarBinding) : this(binding.root) {
            itemBinding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAvatarVH {
        val itemAvatarBinding = DataBindingUtil.inflate<ItemAvastarBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_avastar,
            parent,
            false
        )
        return ItemAvatarVH(itemAvatarBinding)
    }

    override fun onBindViewHolder(holder: ItemAvatarVH, position: Int) {
        val avatarBean = avatarList[position]
        holder.itemBinding?.itemAvatarBean = avatarBean
    }

    override fun getItemCount(): Int {
        return avatarList.size
    }

    public fun initData() {
        avatarList.clear()
        avatarList.addAll(ItemAvatarBeanUtils.getData())
        notifyDataSetChanged()
    }
}