package com.work.theIsle.kotlin

import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.work.baselib.arouter.RouterPath.PATH_KOTLINACTIVITY
import com.work.login.bean.KotlinUserBean
import com.work.supportlib.LoggerUtils
import com.work.theIsle.R
import com.work.theIsle.app.BaseAppActivity
import com.work.theIsle.databinding.ActivityKotlinBinding
import com.work.theIsle.kotlin.vm.KotlinViewModel

@Route(path = PATH_KOTLINACTIVITY)
class KotlinActivity : BaseAppActivity() {
    private lateinit var binding: ActivityKotlinBinding
    private val userModel: KotlinViewModel by viewModels()

    @Autowired(name = "key")
    lateinit var userBean: KotlinUserBean

    override fun initView() {
        ARouter.getInstance().inject(this)
        userModel.getKotlinUserMutable().value = userBean
        binding = DataBindingUtil.setContentView(this, R.layout.activity_kotlin)
        binding.lifecycleOwner = this
        binding.userModel = userModel
        binding.btnReified.setOnClickListener {
            reifiedShow()
        }
        binding.btnExtendFunc.setOnClickListener {
            extendFuncShow()
        }

        binding.btnDemo.setOnClickListener {
            //demoShow()
            //demoShow2()
            //demoShow3()
            demoShow4()
        }
    }

    private fun demoShow4() {
        val names = listOf("A", "B", "C","D")
        val ages = listOf(1, 2, 3)
        //names.zipWithNext { a, b -> "next $a $b" }.printlnLog()
        //names.zip(ages).printlnLog()
        val result = names.zip(ages).toMap()
        result["B"]?.printlnLog()
    }

    private fun demoShow3() {
        val result = listOf(listOf(1, 2, 3), listOf(4, 5, 6)).flatMap { it ->
            it.filter { it == 2 || it == 6 }
        }
        result.printlnLog()
    }

    private fun demoShow2() {
        val result = listOf("A", "B", "C")
            .filter { !it.contains('A') }
        result.printlnLog()
    }

    private fun demoShow() {
        val bean = DataBean()
        var result: List<Int?> = listOf(listOf(1, null, 3), listOf(1, 2, 3)).flatten()
        val setResult = result.toSet()
        /* result = result.mapIndexed { index, value ->
             index * value
         }*/
        /*result = result.mapIndexedNotNull { index, value ->
            if (value != null) {
                index + value
            }else{
                -1
            }
        }*/
        // 定义方法写法
        result.mapIndexedNotNull(getTransform()).printlnLog()
        //result.printlnLog()
    }

    private fun getTransform(): (index: Int, value: Int?) -> String {
        return { index, value ->
            if (value != null) {
                (index * value).toString()
            } else {
                "-1"
            }
        }
    }


    private fun extendFuncShow() {
        LoggerUtils.i("abc".addExt(6))
        3.printlnLog()
    }

    private fun reifiedShow() {
        val box: MagiBox<Man> = MagiBox()
        val subject: Man = box.randomDataOrBack { name, age ->
            Man(name, age)
        }
        LoggerUtils.i("name = ${subject.name} age = ${subject.age}")
    }

    override fun initData() {

    }
}