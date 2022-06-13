package com.work.httplib;

import android.text.TextUtils;

import com.work.httplib.dn.kt.KTBaseResponse;
import com.work.httplib.handle.HttpErrorDetail;

import io.reactivex.rxjava3.functions.Function;

/**
 * Created by zhaojian on 2018/2/5.
 * 网络错误预处理类；
 */

public class AnnHttpError<T> implements Function<Throwable, KTBaseResponse<T>> {
    @Override
    public KTBaseResponse<T> apply(Throwable throwable) {
        //Logger.e("接口请求网络错误: "+throwable.getMessage() + getStackTraceDetail(throwable.fillInStackTrace().getStackTrace()));
        String thrStr = throwable.toString();
        HttpErrorDetail error = new HttpErrorDetail();
        String errorCode = null, errorMsg = null;

        if (thrStr.contains(HttpErrorDetail.HTTP_ERROR_NO_CONNECT)) {
            errorCode = HttpErrorDetail.HTTP_ERROR_NO_CONNECT;
            errorMsg = "无法连接到服务器";
        }

        if (thrStr.contains(HttpErrorDetail.HTTP_ERROR_TIME_OUT)) {
            errorCode = HttpErrorDetail.HTTP_ERROR_TIME_OUT;
            errorMsg = "无法连接到服务器";
        }

        if (thrStr.contains(HttpErrorDetail.HTTP_ERROR_SERVER_CLOSE)) {
            errorCode = HttpErrorDetail.HTTP_ERROR_SERVER_CLOSE;
            errorMsg = "无法连接到服务器";
        }

        if (thrStr.contains(HttpErrorDetail.HTTP_ERROR_HOST_FAILED)) {
            errorCode = HttpErrorDetail.HTTP_ERROR_HOST_FAILED;
            errorMsg = "无法连接到服务器";
        }

        if (thrStr.contains(HttpErrorDetail.HTTP_ERROR_410)) {
            errorCode = HttpErrorDetail.HTTP_ERROR_410;
            errorMsg = "您的账号已在其他地方登录，请注意帐号安全";
        }

        if (thrStr.contains(HttpErrorDetail.HTTP_ERROR_404)) {
            errorCode = HttpErrorDetail.HTTP_ERROR_404;
            errorMsg = "接口未找到";
        }

        if (thrStr.contains(HttpErrorDetail.HTTP_ERROR_401)) {
            errorCode = HttpErrorDetail.HTTP_ERROR_401;
            errorMsg = "账号已在其他地方登录";
        }

        if (thrStr.contains(HttpErrorDetail.HTTP_ERROR_GSON_JSON_SYNTAX_EXCEPTION)) {
            errorCode = HttpErrorDetail.HTTP_ERROR_GSON_JSON_SYNTAX_EXCEPTION;
            errorMsg = "数据为空";
        }

        if (thrStr.contains(HttpErrorDetail.HTTP_ERROR_BODY_NULL)) {
            errorCode = HttpErrorDetail.HTTP_ERROR_BODY_NULL;
            errorMsg = "解析服务器数据失败";
        }

        if (thrStr.contains(HttpErrorDetail.HTTP_ERROR_GSON_JSON_OTHER_EXCEPTION)) {
            errorCode = HttpErrorDetail.HTTP_ERROR_GSON_JSON_OTHER_EXCEPTION;
            errorMsg = "解析服务器数据失败";
        }

        if (errorCode == null) {
            errorCode = HttpErrorDetail.HTTP_ERROR_UNKNOWN;
            errorMsg = "解析服务器数据失败";
        }
        if (TextUtils.isEmpty(errorMsg)) {
            errorMsg = "未知错误";
        }

        KTBaseResponse<T> bean = new KTBaseResponse<>("-1", errorMsg);


        /*if (throwable instanceof CustomHttpException) {
            bean.logId = ((CustomHttpException) throwable).getLogId();
            error.errorMsg = bean.logId + ":" + error.errorMsg;
        }
*/
        return bean;
    }

    private String getStackTraceDetail(StackTraceElement[] stackTraceElements) {
        String result = "";
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            result = result + "\nat " + stackTraceElement;
        }
        return result;
    }
}
