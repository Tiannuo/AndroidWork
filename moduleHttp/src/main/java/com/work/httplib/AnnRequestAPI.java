package com.work.httplib;

import com.tikou.modulehttpannotation.InvokeByProxy;
import com.work.httplib.data.AnnRequestParams;
import com.work.httplib.dn.kt.KTBaseResponse;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author Administrator
 * @Date 2022/6/10-17:53
 * @Email wangweitikou1994@gmail.com
 * @Des MethodEntity 接口方法参数
 * * 服务器接口定义
 * * 用法：在此处定义好接口后，重新make project一次，然后从{@link RequestProxy}类中调用相应的方法；
 */

public interface AnnRequestAPI {
    /*获取情话接口*/
    @InvokeByProxy
    @POST("rand.qinghua")
    Observable<KTBaseResponse<String>> loadQing(@Body AnnRequestParams requestParams);
}
