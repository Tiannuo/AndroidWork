package com.work.theIsle.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.orhanobut.logger.Logger
import com.work.baselib.activity.BaseActivity
import com.work.baselib.arouter.RouterPath.PATH_FLOWACTIVITY
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R

/**
 * @Author TIKOU
 * @Date 2022/7/4-18:13
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@Route(path = PATH_FLOWACTIVITY)
class FlowActivity : BaseActivity() {
    override fun initView() {
        setContentView(R.layout.activity_flow)
    }

    override fun initData() {
        showTestValues()
    }

    private fun showTestValues() {
        simpleList().forEach {
            LoggerUtils.i(it)
        }
    }

    fun simpleList(): List<Int> = listOf(1, 2, 3, 4)
}