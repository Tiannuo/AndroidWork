package com.work.theIsle.hilt.httpProcessor

/**
 * @Author TIKOU
 * @Date 2022/7/13-18:48
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description 再kotlin中静态量用companion object
 */
class HttpHelper {
    fun post(url: String, params: Map<String, Any>, callback: ICallback) {
        mIHttpProcessor!!.post(url, params, callback)
    }

    companion object {
        private var mIHttpProcessor: IHttpProcessor? = null

        @Volatile
        private var instance: HttpHelper? = null
        fun init(iHttpProcessor: IHttpProcessor?) {
            mIHttpProcessor = iHttpProcessor
        }

        fun obtain(): HttpHelper? {
            synchronized(HttpHelper::class.java) {
                if (instance == null) {
                    instance = HttpHelper()
                }
                return instance
            }
        }
    }
}