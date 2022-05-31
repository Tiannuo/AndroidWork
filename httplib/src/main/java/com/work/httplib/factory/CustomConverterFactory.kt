package com.work.httplib.factory

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.work.httplib.converter.CustomResponseBodyConverter
import com.work.httplib.converter.UrlFormBodyConverter
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by ww on 2018/2/5.
 * 自定义消息转换工厂；
 * 发送的消息通过urlForm的格式转换；
 * 接收的消息通过Gson转换；
 */
class CustomConverterFactory private constructor(private val gson: Gson) : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return CustomResponseBodyConverter(gson, adapter)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        return UrlFormBodyConverter<Any>()
    }

    companion object {
        fun create(gson: Gson?): CustomConverterFactory {
            if (gson == null) throw NullPointerException("gson == null")
            return CustomConverterFactory(gson)
        }
    }
}