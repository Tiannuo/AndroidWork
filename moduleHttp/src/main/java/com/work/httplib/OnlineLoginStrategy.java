package com.work.httplib;

import com.work.httplib.data.AnnRequestParams;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.reactivex.rxjava3.core.Observable;

/**
 * @Author Administrator
 * @Date 2022/6/10-17:53
 * @Email wangweitikou1994@gmail.com
 * @Des AnnILoginStrategy 注解生成策略
 * OnlineLoginStrategy 在线策略处理
 */

public class OnlineLoginStrategy implements AnnILoginStrategy {
    @Override
    public Observable handleRequest(AnnRequestParams requestParams) {
        return null;
    }
/*    @Override
    public Observable handleRequest(AnnRequestParams requestParams) {
        try {
            Method method = OnlineRequestProxy.class.getMethod(requestParams.localMethodName, AnnRequestParams.class);
            return (Observable) method.invoke(OnlineRequestProxy.class, requestParams);
        } catch (NoSuchMethodException e) {
            //Logger.e(e.getMessage());
        } catch (IllegalAccessException e) {
            //Logger.e(e.getMessage());
        } catch (InvocationTargetException e) {
            //Logger.e(e.getTargetException().getMessage());
        }
        //Logger.e("method not found----->" + requestParams.method);
        return null;
    }*/
}
