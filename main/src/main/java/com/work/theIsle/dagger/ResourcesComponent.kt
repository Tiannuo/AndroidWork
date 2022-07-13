package com.work.theIsle.dagger

import dagger.Component
import javax.inject.Named

/**
 * @Author TIKOU
 * @Date 2022/7/11-10:01
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@Component(modules = [ResourcesModule::class])
interface ResourcesComponent {
    fun provideResourcesObject(): ResourcesObject
    @Named("key1")
    fun provideStubUser(): StubUser
    @Named("key2")
    fun provideStubUser2(): StubUser
}