package com.work.login

data class LoginModel(val state: String,val data:LoginData)
data class LoginData(val dataString:String)