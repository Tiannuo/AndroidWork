package com.work.httplib.transients

/**
 * Created by ww on 2018/3/29.
 * Gson转换时是否要忽略此字段，默认序列化和反序列化都忽略此字段
 */
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class GsonTransient(
    /**
     * @return 序列化时是否要忽略此字段
     */
    val serialize: Boolean = true,
    /**
     * @return 反序列化时是否忽略此字段
     */
    val deserialize: Boolean = true
)