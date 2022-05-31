package com.work.httplib.params

/**
 * @author zhaojian
 * @time 2018/12/4 15:06
 * @describe
 */
class CommonParams {
    /*登录返回的accesstoken*/
    var authorization: String? = null

    /*经度*/
    var longitude: String? = null

    /*纬度*/
    var latitude: String? = null

    companion object {
        /*对应登录接口返回的accessToken，需要鉴权的接口会对koken进行校验*/
        const val AUTHORIZATION = "Authorization"

        /*对应授权码接口返回的xtjgbh，所有请求都需要带这个参数，否则无法匹配到路由*/
        const val X_INSTANCE = "X-INSTANCE"

        /*经度*/
        const val X_LONGITUDE = "X-longitude"

        /*纬度*/
        const val X_LATITUDE = "X-latitude"

        /*设备id*/
        const val X_DEVICEID = "X-DeviceId"

        /*手机品牌*/
        const val X_MOBILEBRAND = "X-DeviceBrand"

        /*手机型号*/
        const val X_MOBILETYPE = "X-DeviceType"

        /*手机系统版本*/
        const val X_MOBILESYSVERSION = "X-SystemVersion"

        /*app版本*/
        const val X_APPVERSION = "X-AppVersion"

        /*业务数据*/
        const val X_USERINFO = "X-userInfo"

        /*平台信息*/
        const val X_PLATFORM = "X-platform"
    }
}