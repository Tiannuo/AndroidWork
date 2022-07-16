package com.work.theIsle.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named

/**
 * @Author TIKOU
 * @Date 2022/7/14-22:48
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@InstallIn(ActivityComponent::class)
@Module
class HiltScopeModule {
    @Provides
    @ActivityScoped
    fun getHiltScopeObject(): HiltScopeObject = HiltScopeObject()

    @Provides
    @ActivityScoped
    fun getTestObject():TestObject = TestObject("init")
}