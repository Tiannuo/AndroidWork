package com.work.httplib;


import com.work.httplib.factory.GsonFactory;
import com.work.httplib.interceptor.UserInfoInterceptor;


import java.util.concurrent.ConcurrentHashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @Author Administrator
 * @Date 2022/6/10-17:53
 * @Email wangweitikou1994@gmail.com
 * @Des AnnAPIManager 接口管理
 **/

public class AnnAPIManager
{
    private static OkHttpClient client;
    private static final ConcurrentHashMap<String, Object> APICache = new ConcurrentHashMap<>();
    private static String mUrl = "";

    static <T> T getAPI(Class<T> clazz)
    {
        if (null == client)
        {
            client = getOkHttpClient();
        }
        T api = (T) APICache.get(clazz.getName());
        if (api == null)
        {
            synchronized (AnnAPIManager.class)
            {
                if (api != null) return api;
                api = new Retrofit.Builder().client(client)

                        .baseUrl(mUrl)
                        .build().create(clazz);
                APICache.put(clazz.getName(), api);
            }
        }
        return api;
    }

    public static OkHttpClient getOkHttpClient()
    {

        return new OkHttpClient.Builder()
                .build();
    }
}
