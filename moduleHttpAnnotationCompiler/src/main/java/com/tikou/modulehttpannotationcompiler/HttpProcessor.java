package com.tikou.modulehttpannotationcompiler;

import static com.tikou.modulehttpannotationcompiler.HttpInfo.PACKAGENAME;
import static com.tikou.modulehttpannotationcompiler.HttpInfo.PACKAGENAMEDATA;
import static com.tikou.modulehttpannotationcompiler.HttpInfo.PACKAGENAMERXJAVAIO;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.tikou.modulehttpannotation.InvokeByProxy;
import com.tikou.modulehttpannotation.ResultMayNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import retrofit2.http.POST;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({ // 标注注解处理器支持的注解类型
        //"com.mobiledoctor.httpannotation.METHOD",
        "com.tikou.modulehttpannotation.ResultMayNull",
        //"com.mobiledoctor.httpannotation.SupportOffline",
        "com.tikou.modulehttpannotation.InvokeByProxy",
        "retrofit2.http.POST",
        //"com.mobiledoctor.httpannotation.TimeOut"
})
public class HttpProcessor extends AbstractProcessor {
    private Filer mFiler;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(METHOD.class);
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(POST.class);
        Set<? extends Element> supportElements = roundEnvironment.getElementsAnnotatedWith(InvokeByProxy.class);
        filterElement(elements, supportElements);
        Set<? extends Element> nullableElements = roundEnvironment.getElementsAnnotatedWith(ResultMayNull.class);
        //Set<? extends Element> offlineElements = roundEnvironment.getElementsAnnotatedWith(SupportOffline.class);
        boolean requestProxySuccess = createRequestProxy(elements, nullableElements);
        boolean onlineRequestProxySuccess = createOnlineRequestProxy(elements, nullableElements);
        //boolean offlineSuccess = createAbstractOfflineStrategy(offlineElements, nullableElements);
        //createTimeOutMap(roundEnvironment.getElementsAnnotatedWith(TimeOut.class));
        return requestProxySuccess && onlineRequestProxySuccess;
        //return requestProxySuccess && onlineRequestProxySuccess && offlineSuccess;
    }


    /**
     * 过滤RequestApi中重复定义的method
     *
     * @param all
     * @param support
     */
    @SuppressWarnings("NewApi")
    private void filterElement(Set<? extends Element> all, Set<? extends Element> support) {
        all.removeIf(element -> !support.contains(element));
    }

    private boolean createRequestProxy(Set<? extends Element> methodElements, Set<? extends Element> nullableElements) {
        try {
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder("RequestProxy")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

            classBuilder.addField(parseField());

            classBuilder.addMethod(parseSetMethod());

            for (Element element : methodElements) {
                boolean isResultMayNull = checkIsResultMayNull(element, nullableElements);
                MethodSpec methodSpec = parseMethod(element, isResultMayNull);
                classBuilder.addMethod(methodSpec);
            }

            JavaFile javaFile = JavaFile.builder(PACKAGENAME, classBuilder.build())
                    .build();

            javaFile.writeTo(mFiler);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean createAbstractOfflineStrategy(Set<? extends Element> offlineElements, Set<? extends Element> nullableElements) {
        try {
            TypeSpec.Builder interfaceBuilder = TypeSpec.classBuilder("AbstractOfflineStrategy")
                    .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT);
            for (Element element : offlineElements) {
                boolean isResultMayNull = checkIsResultMayNull(element, nullableElements);
                MethodSpec methodSpec = parseOfflineMethod(element, isResultMayNull);
                interfaceBuilder.addMethod(methodSpec);
            }

            JavaFile javaFile = JavaFile.builder(PACKAGENAME, interfaceBuilder.build())
                    .build();

            javaFile.writeTo(mFiler);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean createOnlineRequestProxy(Set<? extends Element> methodElements, Set<? extends Element> nullableElements) {
        try {
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder("OnlineRequestProxy")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

            for (Element element : methodElements) {
                boolean isResultMayNull = checkIsResultMayNull(element, nullableElements);
                MethodSpec methodSpec = parseOnlineMethod(element, isResultMayNull);
                classBuilder.addMethod(methodSpec);
            }

            JavaFile javaFile = JavaFile.builder(PACKAGENAME, classBuilder.build())
                    .build();

            javaFile.writeTo(mFiler);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkIsResultMayNull(Element element, Set<? extends Element> elements) {
        for (Element element1 : elements) {
            if (element1.equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();

    }

    private FieldSpec parseField() {
        ClassName loginStrategy = ClassName.get(PACKAGENAME, "AnnILoginStrategy");
        ClassName onlineLoginStrategy = ClassName.get(PACKAGENAME, "OnlineLoginStrategy");
        return FieldSpec.builder(loginStrategy, "loginStrategy")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .initializer(CodeBlock.of("new $T()", onlineLoginStrategy)).build();
    }

    private MethodSpec parseSetMethod() {
        ClassName loginStrategy = ClassName.get(PACKAGENAME, "AnnILoginStrategy");
        ClassName requestProxy = ClassName.get(PACKAGENAME, "RequestProxy");
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("setLoginStrategy")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addStatement("$T.loginStrategy = loginStrategy", requestProxy)
                .addParameter(loginStrategy, "loginStrategy");
        return methodBuilder.build();
    }

    private MethodSpec parseMethod(Element element, boolean isResultMayNull) {
        if (element.getKind() != ElementKind.METHOD) {
            throw new IllegalArgumentException(String.format("Only method can be annotated with @%s",
                    POST.class.getSimpleName()));
        }

        ExecutableElement executableElement = (ExecutableElement) element;
        MethodEntity methodEntity = new MethodEntity(executableElement);

        ClassName inputType = ClassName.get("java.util", "Map");
        ClassName requestParam = ClassName.get(PACKAGENAMEDATA, "AnnRequestParams");

        MethodSpec.Builder methodBuilder;
        if (isResultMayNull) {
            methodBuilder = MethodSpec.methodBuilder(element.getSimpleName().toString())
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(methodEntity.getReturnType())
                    .addStatement("$T requestParams = new $T()", requestParam, requestParam)
                    .addStatement("requestParams.paramMap = paramMap")
                    .addStatement("requestParams.localMethodName = $S", element.getSimpleName().toString())
                    .addStatement("requestParams.method = $S", methodEntity.getAnnotationValue())
                    .addStatement("return loginStrategy.handleRequest(requestParams)")
                    .addParameter(inputType, "paramMap");
        } else {
            methodBuilder = MethodSpec.methodBuilder(element.getSimpleName().toString())
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(methodEntity.getInnerType())
                    .addStatement("$T requestParams = new $T()", requestParam, requestParam)
                    .addStatement("requestParams.paramMap = paramMap")
                    .addStatement("requestParams.localMethodName = $S", element.getSimpleName().toString())
                    .addStatement("requestParams.method = $S", methodEntity.getAnnotationValue())
                    .addStatement("return loginStrategy.handleRequest(requestParams)")
                    .addParameter(inputType, "paramMap");
        }
        return methodBuilder.build();
    }

    private MethodSpec parseOfflineMethod(Element element, boolean isResultMayNull) {
        if (element.getKind() != ElementKind.METHOD) {
            throw new IllegalArgumentException(String.format("Only method can be annotated with @%s",
                    POST.class.getSimpleName()));
        }

        ExecutableElement executableElement = (ExecutableElement) element;
        MethodEntity offlineEntity = new MethodEntity(executableElement);

        ClassName requestParam = ClassName.get(PACKAGENAMEDATA, "RequestParams");
        ClassName observable = ClassName.get(PACKAGENAMERXJAVAIO, "Observable");
        ClassName callable = ClassName.get("java.util.concurrent", "Callable");
        MethodSpec.Builder methodBuilder;
        if (isResultMayNull) {
            methodBuilder = MethodSpec.methodBuilder(element.getSimpleName().toString())
                    .addModifiers(Modifier.PUBLIC)
                    .returns(offlineEntity.getReturnType())
                    .addStatement("return $T.error(new $T<Throwable>(){" +
                            "@Override " +
                            "public Throwable call() throws Exception{" +
                            " return new Throwable(\"" + element.getSimpleName().toString() + "方法离线接口未实现，请在OfflineLoginStrategy.java中实现\");" +
                            "}})", observable, callable)
                    .addParameter(requestParam, "requestParams");
        } else {
            methodBuilder = MethodSpec.methodBuilder(element.getSimpleName().toString())
                    .addModifiers(Modifier.PUBLIC)
                    .returns(offlineEntity.getInnerType())
                    .addStatement("return $T.error(new $T<Throwable>(){" +
                            "@Override " +
                            "public Throwable call() throws Exception{" +
                            " return new Throwable(\"" + element.getSimpleName().toString() + "方法离线接口未实现，请在OfflineLoginStrategy.java中实现\");" +
                            "}})", observable, callable)
                    .addParameter(requestParam, "requestParams");
        }
        return methodBuilder.build();
    }

    private MethodSpec parseOnlineMethod(Element element, boolean isResultMayNull) {
        if (element.getKind() != ElementKind.METHOD) {
            throw new IllegalArgumentException(String.format("Only method can be annotated with @%s",
                    POST.class.getSimpleName()));
        }

        ExecutableElement executableElement = (ExecutableElement) element;
        MethodEntity methodEntity = new MethodEntity(executableElement);

        ClassName inputType = ClassName.get("java.util", "Map");
        ClassName requestParam = ClassName.get(PACKAGENAMEDATA, "AnnRequestParams");
        ClassName apiManger = ClassName.get(PACKAGENAME, "AnnAPIManager");
        ClassName requestAPI = ClassName.get(PACKAGENAME, "AnnRequestAPI");
        ClassName httpError = ClassName.get(PACKAGENAME, "AnnHttpError");
        ClassName preProcessFlatMap = ClassName.get(PACKAGENAME, "PreProcessFlatMap");
        TypeName httpErrorType = ParameterizedTypeName.get(httpError, methodEntity.getRawType());
        TypeName preProcessFlatMapType = ParameterizedTypeName.get(preProcessFlatMap, methodEntity.getRawType());

        if (isResultMayNull) {
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(element.getSimpleName().toString())
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(methodEntity.getReturnType())
                    .addStatement("return $T.getAPI($T.class).$L(requestParams)" +
                                    ".onErrorReturn(new $T())",
                            apiManger, requestAPI, executableElement.getSimpleName(), httpErrorType)
                    .addParameter(requestParam, "requestParams");
            return methodBuilder.build();
        } else {
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(element.getSimpleName().toString())
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(methodEntity.getInnerType())
                    .addStatement("requestParams.localMethodName = $S", element.getSimpleName().toString())
                    .addStatement("return $T.getAPI($T.class).$L(requestParams)" +
                                    ".onErrorReturn(new $T())" +
                                    ".flatMap(new $T())",
                            apiManger, requestAPI, executableElement.getSimpleName(), httpErrorType, preProcessFlatMapType)
                    .addParameter(requestParam, "requestParams");
            return methodBuilder.build();
        }
    }

/*    private boolean createTimeOutMap(Set<? extends Element> methodElements) {
        try {
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder("TimeOutConfig")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

            classBuilder.addField(parseTimeOutField("readTimeOutMap"));
            classBuilder.addField(parseTimeOutField("writeTimeOutMap"));
            classBuilder.addField(parseTimeOutField("connectTimeOutMap"));

            CodeBlock.Builder builder = CodeBlock.builder();
            for (Element element : methodElements) {
                TimeOut timeOut = element.getAnnotation(TimeOut.class);
                int type = timeOut.type();
                int value = timeOut.value();
                POST post = element.getAnnotation(POST.class);
                String methodName = post.value();
                switch (type) {
                    case TimeOut.READ:
                        builder.add("readTimeOutMap.put($S, $L);\n", methodName, value);
                        break;
                    case TimeOut.WRITE:
                        builder.add("writeTimeOutMap.put($S, $L);\n", methodName, value);
                        break;
                    case TimeOut.CONNECT:
                        builder.add("connectTimeOutMap.put($S, $L);\n", methodName, value);
                        break;
                    case TimeOut.ALL:
                        builder.add("readTimeOutMap.put($S, $L);\n", methodName, value);
                        builder.add("writeTimeOutMap.put($S, $L);\n", methodName, value);
                        builder.add("connectTimeOutMap.put($S, $L);\n", methodName, value);
                        break;
                }
            }
            CodeBlock codeBlock = builder.build();
            classBuilder.addStaticBlock(codeBlock);

            JavaFile javaFile = JavaFile.builder(PACKAGENAME, classBuilder.build())
                    .build();

            javaFile.writeTo(mFiler);
            return true;
        } catch (Exception e) {
            return false;
        }
    }*/

    private FieldSpec parseTimeOutField(String name) {
        ParameterizedTypeName map = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(Integer.class));
        ParameterizedTypeName hashMap = ParameterizedTypeName.get(
                ClassName.get(HashMap.class),
                ClassName.get(String.class),
                ClassName.get(Integer.class));
        return FieldSpec.builder(map, name)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .initializer(CodeBlock.of("new $T()", hashMap)).build();
    }
}
