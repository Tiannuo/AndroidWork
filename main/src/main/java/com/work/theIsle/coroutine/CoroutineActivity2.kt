package com.work.theIsle.coroutine

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import com.work.httplib.httputils.HttpUtils
import com.work.login.api.UserApi
import com.work.theIsle.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**

 * @Author Administrator
 * @Date 2022/5/30-14:40
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
class CoroutineActivity2 : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
    }

    fun showTestResult(view: View) {
        launch {
            val userData = HttpUtils.createApi(UserApi::class.java).loadQing("json")
            Logger.d(userData.content)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}