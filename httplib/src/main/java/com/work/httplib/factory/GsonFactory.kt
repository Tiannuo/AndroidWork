package com.work.httplib.factory

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.work.httplib.adapter.IntegerDefault0Adapter
import com.work.httplib.adapter.DoubleDefault0Adapter
import com.work.httplib.adapter.LongDefault0Adapter
import com.work.httplib.adapter.DefaultObjectTypeAdapter
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.work.httplib.transients.GsonTransient
import com.google.gson.TypeAdapterFactory
import com.work.supportlib.ReflectUtils
import java.lang.Exception
import java.lang.reflect.Field

object GsonFactory {
    fun createCustomGson(): Gson {
        val gson = GsonBuilder()
            .registerTypeAdapter(Int::class.java, IntegerDefault0Adapter())
            .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerDefault0Adapter())
            .registerTypeAdapter(Double::class.java, DoubleDefault0Adapter())
            .registerTypeAdapter(Double::class.javaPrimitiveType, DoubleDefault0Adapter())
            .registerTypeAdapter(Long::class.java, LongDefault0Adapter())
            .registerTypeAdapter(Long::class.javaPrimitiveType, LongDefault0Adapter())
            .registerTypeAdapterFactory(DefaultObjectTypeAdapter.FACTORY)
            .disableHtmlEscaping()
            .addSerializationExclusionStrategy(object : ExclusionStrategy {
                override fun shouldSkipField(f: FieldAttributes): Boolean {
                    val gsonTransient = f.getAnnotation(GsonTransient::class.java)
                    return gsonTransient != null && gsonTransient.serialize
                }

                override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                    return false
                }
            })
            .addDeserializationExclusionStrategy(object : ExclusionStrategy {
                override fun shouldSkipField(arg0: FieldAttributes): Boolean {
                    val gsonTransient = arg0.getAnnotation(
                        GsonTransient::class.java
                    )
                    return gsonTransient != null && gsonTransient.deserialize
                }

                override fun shouldSkipClass(arg0: Class<*>?): Boolean {
                    return false
                }
            })
            .create()


        //通过反射替换gson中factories集合中系统自带的ObjectTypeAdapter为我们自定义的DefaultObjectTypeAdapter
        //在此Adapter中对数字类型进行处理
        var field: Field? = null //Object是已经被赋值的对象实例
        try {
            field = gson.javaClass.getDeclaredField("factories")
            field.isAccessible = true
            val factories = field[gson] as List<TypeAdapterFactory>
            val pro = ReflectUtils.getDeclaredField(factories, "list")
            pro.isAccessible = true
            val modifyerList = pro[factories] as MutableList<TypeAdapterFactory>
            modifyerList.removeAt(1)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return gson
    }
}