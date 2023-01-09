package com.work.theIsle.coroutine.flow

import android.view.View
import com.work.theIsle.coroutine.vm.FlowPracticeVM

/**
 * @Author Admini
 * @Date 2023/1/9-17:40
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class FlowPracticeListener() {
    // Databinding的调用点击事件不可以是suspend
    public fun test(v: View, vm: FlowPracticeVM, type: FlowType) {
        vm.getData("测试", vm,type)
    }
}