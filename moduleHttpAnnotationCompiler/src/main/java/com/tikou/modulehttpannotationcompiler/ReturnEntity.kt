package com.tikou.modulehttpannotationcompiler

import javax.lang.model.type.TypeMirror

/**
 *
 * @Author Administrator
 * @Date 2022/6/10-17:53
 * @Email wangweitikou1994@gmail.com
 * @Des ReturnEntity 接口方法返回结果
 */
class ReturnEntity {
    /*Observable<BaseResultBean<String>>*/
    var returnType: TypeMirror? = null

    /*String*/
    var rawType: TypeMirror? = null
}