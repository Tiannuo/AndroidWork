package com.work.article

import android.view.View
import com.work.supportlib.LoggerUtils

/**
 * @Author Admini
 * @Date 2023/1/5-17:44
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class ClickListener {
    fun clickTest(v: View, vm: IndexViewModel) {
        //LoggerUtils.e("clickTest= $msg")
        vm.getUser("Test")
    }
}