package com.tikou.modulehttpannotation

/**
 * @Author Administrator
 * @Date 2022/6/10-17:44
 * @Email wangweitikou1994@gmail.com
 * @Des Gson转换时是否要忽略此字段，默认序列化和反序列化都忽略此字段
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
//@kotlin.annotation.Target
//@Target(AnnotationTarget.FIELD)
annotation class GsonTransient(
    /**
     * serialize 序列化时是否要忽略此字段
     * deserialize 反序列化时是否忽略此字段
     */
    val serialize: Boolean = true, val deserialize: Boolean = true

)