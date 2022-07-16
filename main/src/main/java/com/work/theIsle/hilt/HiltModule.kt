package com.work.theIsle.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author TIKOU
 * @Date 2022/7/14-21:51
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@InstallIn(SingletonComponent::class)
@Module
class HiltModule {
    @Provides
    @Singleton
    fun getHiltObject(): HiltObject = HiltObject()

}