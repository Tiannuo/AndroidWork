package com.work.httplib.config

object TimeOutConfig {
    var readTimeOutMap: MutableMap<String, Int> = HashMap()
    var writeTimeOutMap: MutableMap<String, Int> = HashMap()
    var connectTimeOutMap: MutableMap<String, Int> = HashMap()

    init {
        readTimeOutMap["getQfycl"] = 3600
        writeTimeOutMap["getQfycl"] = 3600
        connectTimeOutMap["getQfycl"] = 3600
    }
}