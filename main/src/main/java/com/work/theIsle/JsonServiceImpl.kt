package com.work.theIsle

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * @Author TIKOU
 * @Date 2022/7/23-22:10
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description  阿里路由需要手动实现相关方法，不然传递对象会报错
 *
 */
@Route(path = "/service/json")
class JsonServiceImpl: SerializationService {

    override fun init(context: Context?) {

    }

    override fun <T : Any?> json2Object(input: String?, clazz: Class<T>?): T {
        return Gson().fromJson(input,clazz)
    }

    override fun object2Json(instance: Any?): String {
        return  Gson().toJson(instance)
    }

    override fun <T : Any?> parseObject(input: String?, clazz: Type?): T {
        return Gson().fromJson(input,clazz)
    }
}