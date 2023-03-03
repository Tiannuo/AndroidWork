package com.work.httplib.httputils

import com.orhanobut.logger.Logger
import com.work.httplib.BuildConfig
import com.work.httplib.BuildConfig.IBASE_URL
import com.work.httplib.interceptor.CommonQueryParamsInterceptor
import com.work.httplib.listener.ResponseListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object HttpUtils {
    private var mClient: OkHttpClient? = null

    fun <T> createApi(clazz: Class<T>): T = Retrofit.Builder()
        .baseUrl(IBASE_URL)
        .client(createClient())
        //对Gson数据预处理,需要配合具体的业务进行完善
        //.addConverterFactory(CustomConverterFactory.create(GsonFactory.createCustomGson()))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(clazz)


    @Synchronized
    private fun createClient(): OkHttpClient {
        if (mClient == null) {
            mClient = OkHttpClient.Builder()
                //打印HTTP 请求信息
                .addInterceptor(createInterceptor())
                .addInterceptor(CommonQueryParamsInterceptor())
                //增加特殊的Cookies
                //.addInterceptor(AddCookiesInterceptor())
                //增加公共基础参数
                //.addInterceptor(UserInfoInterceptor.instance)
                //特殊业务设置超时拦截
                //.addInterceptor(DynamicTimeoutInterceptor())
                .build()
        }
        return mClient as OkHttpClient
    }

    private fun createInterceptor(): Interceptor {
        HttpLoggingInterceptor.Logger { msg ->
            if (BuildConfig.DEBUG) {
                Logger.json(msg)
            }
        }
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun <T> sendHttp(ob: Observable<T>, lis: ResponseListener<T>) {
        ob.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T> {
                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(t: T) {
                    lis.onSuccess(t)
                }

                override fun onError(e: Throwable?) {
                    lis.onFail(e!!.message.toString())
                }

                override fun onComplete() {

                }

            })

    }


}