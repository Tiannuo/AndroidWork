package com.work.theIsle.jetpack.adapter

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @Author TIKOU
 * @Date 2022/7/20-15:42
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class ImageViewBindingAdapter {

    companion object {
        // @BindingAdapter("image")必须使用静态方法，同时kotlin中使用需要@JvmStatic用于伴生对象的静态函数生成
        //defaultImage
        @BindingAdapter(value = ["image", "defaultImage"], requireAll = false)
        @JvmStatic
        fun setImage(iv: ImageView, url: String?, localRes: Int?) {
            if (!TextUtils.isEmpty(url)) {
                Glide.with(iv).load(url).into(iv)
            } else if (localRes != 0) {
                Glide.with(iv).load(localRes).into(iv)
            }
        }
    }
}