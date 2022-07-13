package com.work.theIsle.dagger

import com.work.theIsle.dagger.scope.AppScope
import dagger.Component
import dagger.Subcomponent

/**
 * @Author TIKOU
 * @Date 2022/7/10-11:27
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 组件用于管理其所属的@Module
 *  组件需要接口定义interface DaggerComponent
 *  @XXXScope 和 @Singleton 都是单例，但是作用域不一样
 *  在需要使用单例的时候，需要保证每一个 @Component 及其 modules 的Scope 一致
 */
@AppScope
@Component(modules = [HttpDataModule::class], dependencies = [PresenterComponent::class,ResourcesComponent::class])
interface DaggerSingletonComponent {
    public fun injectActivity(activity: DaggerActivity)
    public fun injectActivity2(activity: DaggerActivity2)
}