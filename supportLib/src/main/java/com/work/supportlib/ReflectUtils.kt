package com.work.supportlib

import java.lang.Exception
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType

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

    fun analysisClassInfo(obj: Any): Class<*> {
        // 在java中T.getClass() 或 T.class都是不合法的，因为T是泛型变量。
        // 由于一个类的类型在编译期已确定，故不能在运行期得到T的实际类型。
        // getGenericSuperclass：获取当前运行类泛型父类类型，即为参数化类型，有所有类型公用的高级接口Type接收。
        // Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        // ParameterizedType参数化类型，即泛型
        // getActualTypeArguments获取参数化类型的数组，泛型可能有多个
        val genType = obj.javaClass.genericSuperclass
        val pType = genType as ParameterizedType
        val params = pType.actualTypeArguments
        val type0 = params[0]
        return type0 as Class<*>
    }
}