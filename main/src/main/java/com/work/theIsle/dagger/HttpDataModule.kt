package com.work.theIsle.dagger

import com.work.theIsle.dagger.scope.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @Author TIKOU
 * @Date 2022/7/9-17:12
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 * @Module 用于提供数据类对象注解
 * @Provides 暴露需要注入的对象
 */
@Module
class HttpDataModule {
    //需要使用单例的具体对象使用Singleton ，其module不需要
    @AppScope
    @Provides
    public fun providerHttpObject() = HttpDataObject()

    @Named("YoungUser")
    @Provides
    public fun getYoungUser() = User("少年",15)

    @Named("OldUser")
    @Provides
    public fun getOldUser() = User("老年",65)
}