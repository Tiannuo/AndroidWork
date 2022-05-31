package com.work.httplib.common;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 通用请求异常封装类
 */
public class ApiException extends Exception {

    private static final int UNKNOWN_ERROR = -0x10;
    private static final int PARSE_ERROR = -0x11;
    private static final int NETWORK_ERROR = -0x12;

    private String code;
    private String errorMsg;

    public ApiException(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Throwable --> ApiException
     *
     * @param e
     * @return
     */
    public static ApiException handleException(Throwable e) {
        if (e instanceof ApiException) {
            return (ApiException) e;
        }
        ApiException ex;
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(String.valueOf(PARSE_ERROR), "数据解析异常");
            return ex;
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException
                || e instanceof SocketTimeoutException) {
            ex = new ApiException(String.valueOf(NETWORK_ERROR), "网络请求异常");
            return ex;
        } else {
            ex = new ApiException(String.valueOf(UNKNOWN_ERROR), "其它异常：" + e.getMessage());
            e.printStackTrace();
            return ex;
        }
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "code='" + code + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
