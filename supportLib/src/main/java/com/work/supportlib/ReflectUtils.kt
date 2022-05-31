package com.work.supportlib

import java.lang.Exception
import java.lang.reflect.Field

object ReflectUtils {
    fun getDeclaredField(`object`: Any, fieldName: String?): Field? {
        var field: Field? = null
        var clazz: Class<*> = `object`.javaClass
        while (clazz != Any::class.java) {
            try {
                field = clazz.getDeclaredField(fieldName)
                return field
            } catch (e: Exception) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
            clazz = clazz.superclass
        }
        return null
    }
}