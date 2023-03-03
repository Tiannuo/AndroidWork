package com.work.theIsle.splash

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hjq.toast.Toaster
import com.work.baselib.arouter.RouterPath.PATH_LOGIN

import com.work.theIsle.R
import com.work.theIsle.databinding.ActivitySplashBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/*
* 启动类就没有必要弄MVP了
* */
class SplashActivity : AppCompatActivity() {
    private var binding: ActivitySplashBinding? = null
    private var tv_remain: TextView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        tv_remain = binding!!.tvRemain
        requestPermission();
        delayFinish();
    }

    /*
    * 请求必要权限
    * */
    private fun requestPermission() {
        XXPermissions.with(this)
            .permission(Permission.MANAGE_EXTERNAL_STORAGE)
            // .permission(Permission.REQUEST_INSTALL_PACKAGES)
            .permission(Permission.READ_PHONE_STATE)
            .permission(Permission.ACCESS_FINE_LOCATION)
            .permission(Permission.ACCESS_COARSE_LOCATION)
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: MutableList<String>, allGranted: Boolean) {
                    if (allGranted) {
                        Toaster.show(R.string.toast_request_permission_success)
                    } else {
                        Toaster.show(R.string.toast_request_permission_not_all_success)
                    }
                }

                override fun onDenied(permissions: MutableList<String>, doNotAskAgain: Boolean) {
                    super.onDenied(permissions, doNotAskAgain)
                    if (doNotAskAgain) {
                        Toaster.show(R.string.toast_request_permission_never)
                    } else {
                        Toaster.show(R.string.toast_request_permission_fail)
                    }
                }

            })


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
                when (it.toInt()) {
                    2 -> skipToLoginActivity()
                }
            }
    }

    private fun skipToLoginActivity() {
        ARouter.getInstance().build(PATH_LOGIN).navigation(this)
        finish()
    }

    private fun skipToMainActivity(it: Long) {

    }

}