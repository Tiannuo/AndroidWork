package com.work.theIsle.hilt.httpProcessor

import com.work.theIsle.annotation.BindOkHttp
import com.work.theIsle.annotation.BindXutils
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author TIKOU
 * @Date 2022/7/16-18:58
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class HttpProcessorModule {
    @BindOkHttp
    @Binds
    @Singleton
    abstract fun bindOkHttp(okhttpProcessor: OkhttpProcessor): IHttpProcessor

    @BindXutils
    @Binds
    @Singleton
    abstract fun bindXutils(xutilsProcessor: XutilsProcessor): IHttpProcessor
}