package com.work.theIsle.dagger

import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @Author TIKOU
 * @Date 2022/7/11-9:59
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@Module
class ResourcesModule {
    @Provides
    public fun provideResourcesObject() = ResourcesObject()

    @Provides
    @Named("key1")
    public fun provideStubUser() = StubUser(1)

    @Provides
    @Named("key2")
    public fun provideStubUser2() = StubUser(2)
}