package com.work.supportlib

import android.text.TextUtils
import com.work.supportlib.AnnotationParseUtil
import retrofit2.http.POST
import java.util.HashMap

/**
 * Created by Tikou on 2019/2/18 11:05.
 * 作者：wangwei
 * 邮箱：ww@winning.com.cn
 * 说明：注解解析工具（获取参数值）
 * 参考：
 */
object AnnotationParseUtil {
    var methodMap = HashMap<String, String>()

    /**
     * @param requestApiMethod T 一般是interface 中的方法名
     * @return RequestApi对应方法中的Post参数取值
     */
    fun <T> getRequestApiMethodValue(requestApiMethod: String, clazz: Class<T>): String? {
        if (TextUtils.isEmpty(requestApiMethod)) {
            return ""
        }
        if (methodMap.isEmpty()) {
            initMethodMap(clazz)
        }
        return methodMap[requestApiMethod]
    }

    /*初始化POST方法的注解值*/
    private fun <T> initMethodMap(clazz: Class<T>) {
        val methods = clazz.declaredMethods
        for (method in methods) {
            if (method.isAnnotationPresent(POST::class.java)) {
                val post = method.getAnnotation(POST::class.java)
                methodMap[method.name] = post.value
            }
        }
    } /*初始化方法的map annotationType = POST.class */ /*   private static <A extends POST, T> void initMethodMap(Class<T> clazz, Class<POST> annotationType) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationType)) {
                A post = (A) method.getAnnotation(annotationType);
                methodMap.put(method.getName(), post.value());
            }
        }
    }*/
}