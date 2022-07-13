package com.work.theIsle.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.orhanobut.logger.Logger
import com.work.theIsle.R
import com.work.theIsle.app.BaseApp
import javax.inject.Inject

class DaggerActivity2 : AppCompatActivity() {
    @Inject
    lateinit var httpDataObject3: HttpDataObject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger2)

        // dager注入方式二
/*        DaggerDaggerComponent2g.builder().httpDataModule(HttpDataModule())
            .build().injectActivity(this)*/

        //全局单例注入
        (application as BaseApp).getDaggerSingleComponent().injectActivity2(this)
        Logger.i("DaggerActivity2Code" + httpDataObject3.hashCode())
    }
}