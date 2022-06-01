package com.work.httplib.adapter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlin.Throws
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.internal.bind.ObjectTypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.work.httplib.adapter.DefaultObjectTypeAdapter
import java.io.IOException
import java.lang.IllegalStateException
import java.util.ArrayList

/**
 * @author zhaojian
 * @time 2019/4/3 10:57
 * @describe 代替Gson中默认的ObjectTypeAdapter，处理《Gson转换错误导致Int变为Double类型》问题
 * 参考：https://www.jianshu.com/p/eafce9689e7d
 */
class DefaultObjectTypeAdapter internal constructor(private val gson: Gson) : TypeAdapter<Any?>() {
    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Any? {
        val token = `in`.peek()
        return when (token) {
            JsonToken.BEGIN_ARRAY -> {
                val list: MutableList<Any?> = ArrayList()
                `in`.beginArray()
                while (`in`.hasNext()) {
                    list.add(read(`in`))
                }
                `in`.endArray()
                list
            }
            JsonToken.BEGIN_OBJECT -> {
                val map: MutableMap<String, Any?> =
                    LinkedTreeMap()
                `in`.beginObject()
                while (`in`.hasNext()) {
                    map[`in`.nextName()] = read(`in`)
                }
                `in`.endObject()
                map
            }
            JsonToken.STRING -> `in`.nextString()
            JsonToken.NUMBER -> {
                val numberStr = `in`.nextString()
                //返回的numberStr不会为null
                if (numberStr.contains(".") || numberStr.contains("e")
                    || numberStr.contains("E")
                ) {
                    numberStr.toDouble()
                } else numberStr.toLong()
            }
            JsonToken.BOOLEAN -> `in`.nextBoolean()
            JsonToken.NULL -> {
                `in`.nextNull()
                null
            }
            else -> throw IllegalStateException()
        }
    }

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Any?) {
        if (value == null) {
            out.nullValue()
            return
        }
        val typeAdapter = gson.getAdapter(value.javaClass) as TypeAdapter<Any>
        if (typeAdapter is ObjectTypeAdapter) {
            out.beginObject()
            out.endObject()
            return
        }
        typeAdapter.write(out, value)
    }

    companion object {
        @JvmField
        val FACTORY: TypeAdapterFactory = object : TypeAdapterFactory {
            override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
                return if (type.rawType == Any::class.java) {
                    DefaultObjectTypeAdapter(gson) as TypeAdapter<T>
                } else null
            }
        }
    }
}