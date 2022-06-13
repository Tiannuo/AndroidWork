package com.tikou.modulehttpannotationcompiler

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.TypeName
import retrofit2.http.POST
import javax.lang.model.element.ExecutableElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeMirror

/**
 *
 * @Author Administrator
 * @Date 2022/6/10-17:53
 * @Email wangweitikou1994@gmail.com
 * @Des MethodEntity 接口方法参数
 */
class MethodEntity(executableElement: ExecutableElement) {
    var annotationValue: String? = null
        private set

    /*Observable<BaseResultBean<String>>*/
    var returnType: TypeName? = null
        private set

    /*Observable<String>*/
    var innerType: TypeName? = null
        private set

    /*String*/
    var rawType: TypeName? = null
        private set
    private val returnEntity = ReturnEntity()
    private fun init(executableElement: ExecutableElement) {
        parseAnnotationValue(executableElement)
        val typeMirror = executableElement.returnType
        parseReturnEntity(typeMirror, returnEntity)
        parseReturnType()
    }

    private fun parseReturnType() {
        returnType = ClassName.get(returnEntity.returnType)
        rawType = ClassName.get(returnEntity.rawType)
        val outer = (returnType as ParameterizedTypeName?)!!.rawType
        innerType = ParameterizedTypeName.get(outer, rawType)
    }

    private fun parseReturnEntity(typeMirror: TypeMirror, returnEntity: ReturnEntity) {
        if (typeMirror is DeclaredType) {
            returnEntity.returnType = typeMirror
            if (typeMirror.typeArguments.size == 1) {
                val raw = typeMirror.typeArguments[0]
                if ((raw as DeclaredType).typeArguments.size == 1) {
                    val tem = raw.typeArguments[0]
                    returnEntity.rawType = tem
                }
            }
        }
    }

    private fun parseAnnotationValue(executableElement: ExecutableElement) {
        val method = executableElement.getAnnotation(POST::class.java)
        annotationValue = method.value
        require(annotationValue!!.isNotEmpty()) {
            String.format(
                "value() in %s for field %s is not valid !", POST::class.java.simpleName,
                executableElement.simpleName
            )
        }
    }

    override fun toString(): String {
        return "annotationValue:$annotationValue|returnEntity:$returnEntity"
    }

    init {
        init(executableElement)
    }
}