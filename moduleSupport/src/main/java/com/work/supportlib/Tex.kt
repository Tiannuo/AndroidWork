package com.work.supportlib

import java.lang.reflect.ParameterizedType

/**
 * @Author Administrator
 * @Date 2022/5/31-14:37
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
object Tex {
    fun analysisClassInfo(obj: Any): Class<*> {
        val genType = obj.javaClass.genericSuperclass
        val pType = genType as ParameterizedType
        val params = pType.actualTypeArguments
        val type0 = params[0]
        return type0 as Class<*>
    }
}