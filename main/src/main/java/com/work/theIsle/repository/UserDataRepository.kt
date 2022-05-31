package com.work.theIsle.repository

import com.work.httplib.httputils.HttpUtils
import com.work.login.api.UserApi
import com.work.login.bean.QingHuaBean

/**

 * @Author Administrator
 * @Date 2022/5/30-15:26
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
class UserDataRepository {
    suspend fun getUserData(): QingHuaBean{
        return HttpUtils.createApi(UserApi::class.java).loadQing()
    }
}