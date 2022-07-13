package com.work.theIsle.dagger

import com.work.theIsle.dagger.scope.UserScope
import dagger.Component

/**
 * @Author TIKOU
 * @Date 2022/7/10-12:54
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
@UserScope
@Component(modules = [PresenterModule::class])
interface PresenterComponent {

    public fun providerPresenter(): Presenter
}