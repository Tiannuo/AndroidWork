package com.work.theIsle.coroutine

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.orhanobut.logger.Logger
import com.work.baselib.arouter.RouterPath.PATH_COROUTINE
import com.work.httplib.httputils.HttpUtils
import com.work.login.api.UserApi
import com.work.theIsle.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.*

@Route(path = PATH_COROUTINE)
class CoroutineActivity : AppCompatActivity() {
    private var mainScope: CoroutineScope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        baseCoroutine()
    }

    /**
     * 原生协程
     */
    private fun baseCoroutine() {
        val continuation = suspend {
            5
        }.createCoroutine(object : Continuation<Int> {
            override val context: CoroutineContext
                get() = EmptyCoroutineContext

            override fun resumeWith(result: Result<Int>) {
                Logger.d("Coroutine End：$result")
            }

        })
        continuation.resume(Unit)
    }

    fun showTestResult(view: View) {
        mainScope.launch {
            val userData = HttpUtils.createApi(UserApi::class.java).loadQing()
            Logger.d(userData.content)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}