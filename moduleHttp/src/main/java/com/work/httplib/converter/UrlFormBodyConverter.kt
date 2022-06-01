package com.work.httplib.converter

import com.work.httplib.factory.GsonFactory
import com.work.httplib.params.RequestParams
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Converter
import java.io.IOException

/**
 * Created by ww on 2018/2/3.
 * urlForm格式消息转换器
 */
class UrlFormBodyConverter<T> : Converter<RequestParams<T>, RequestBody> {
    private val gson = GsonFactory.createCustomGson()

    @Throws(IOException::class)
    override fun convert(requestParams: RequestParams<T>): RequestBody {
        return getParams(requestParams.paramMap)
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    }

    private fun getParams(paramMap: Map<String, T>?): String {
        return if (paramMap == null || paramMap.isEmpty()) {
            ""
        } else gson.toJson(paramMap)
    }
}