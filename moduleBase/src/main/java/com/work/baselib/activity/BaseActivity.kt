package com.work.baselib.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.work.baselib.R

/*
* 仅仅是设置一个基础BASE
* */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStatus()
        initBaseMvpModel()
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()

    open fun initBaseMvpModel() {

    }

    /**
     * 初始化状态栏等操作
     */
    private fun initStatus() {
        supportActionBar?.title = localClassName
    }
}