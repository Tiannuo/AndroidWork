package com.work.theIsle.splash

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.work.theIsle.databinding.ActivitySplashBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    private var binding: ActivitySplashBinding? = null
    private var tv_remain: TextView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        tv_remain = binding!!.tvRemain
        delayFinish();
    }

    /*
    * 延迟 关闭启动界面
    *
    * */
    private fun delayFinish() {
        Observable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.e("===it", "$it")
                tv_remain!!.text = 3.minus(it).toString().plus("秒")
            }
    }

    private fun skipToMainActivity(it: Long) {
    }

}