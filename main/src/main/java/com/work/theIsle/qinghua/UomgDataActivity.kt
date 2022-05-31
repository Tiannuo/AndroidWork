package com.work.theIsle.qinghua

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.work.baselib.arouter.RouterPath.PATH_UOMGDATA
import com.work.httplib.dn.kt.KTApiException
import com.work.httplib.dn.kt.KTICallback
import com.work.httplib.dn.kt.KTRequestApi
import com.work.httplib.httputils.HttpUtils
import com.work.login.api.UserObsApi
import com.work.theIsle.databinding.ActivityUomgBinding

/**

 * @Author Administrator
 * @Date 2022/5/31-19:25
 * @Email wangweitikou1994@gmail.com
 * @Des https://api.uomg.com/doc-rand.qinghua.html uomg 相关接口测试
 */
@Route(path = PATH_UOMGDATA)
class UomgDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUomgBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUomgBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnQinghua.setOnClickListener {
            KTRequestApi.request(
                this,
                HttpUtils.createApi(UserObsApi::class.java).loadQing(),
                object : KTICallback<String> {
                    override fun onSuccess(data: String) {
                        binding.tvShow.text = data
                    }

                    override fun onFailure(e: KTApiException) {
                        binding.tvShow.text = e.errorMsg
                    }
                })
        }
    }
}