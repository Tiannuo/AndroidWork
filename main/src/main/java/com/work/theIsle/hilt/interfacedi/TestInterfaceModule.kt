package com.work.theIsle.hilt.interfacedi

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

/**
 * @Author TIKOU
 * @Date 2022/7/15-16:53
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@InstallIn(ActivityComponent::class)
@Module
abstract class TestInterfaceModule {
    @Binds
    @Named("TestClass")
    abstract fun test(testClazz: TestClass): TestInterface

    @Binds
    @Named("TestClass2")
    abstract fun test2(testClazz: TestClass2): TestInterface
}