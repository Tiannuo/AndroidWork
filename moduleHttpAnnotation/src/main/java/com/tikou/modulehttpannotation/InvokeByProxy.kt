package com.tikou.modulehttpannotation

/**

 * @Author Administrator
 * @Date 2022/6/10-17:53
 * @Email wangweitikou1994@gmail.com
 * @Des 通过自动生成RequestProxy类调用的接口
 */
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class InvokeByProxy {
}