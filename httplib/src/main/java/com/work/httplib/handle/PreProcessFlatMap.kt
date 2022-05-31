package com.work.httplib.handle

import android.text.TextUtils
import com.work.httplib.bean.BaseResultBean
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.functions.Function

/**
 * Created by ww on 2018/2/5.
 * http回调消息预处理
 * onerrorreturn
 */
class PreProcessFlatMap<T> : Function<BaseResultBean<T?>, ObservableSource<T>> {
    override fun apply(result: BaseResultBean<T?>): Observable<T> {
        return Observable.create<T> { subscriber ->
            if (result.code == 0) {
                if (result.data != null) {
                    subscriber.onNext(result.data)
                } else {
                    subscriber.onError(Throwable(result.message))
                }
                subscriber.onComplete()
            } else {
                // 有网络错误
                //saveLog(result);
                if (-1 == result.code && null != result.errorDetail) {
                    val apiException = ApiException(
                        ApiException.TYPE_HTTP,
                        result.errorDetail!!,
                        result.code,
                        result
                    )
                    subscriber.onError(apiException)
                    subscriber.onComplete()
                } else {
                    var errorMsg: String? = ""
                    if (!TextUtils.isEmpty(result.message)) {
                        errorMsg = result.message
                    }
                    //Logger.d("code:" + result.code + " - msg:" + errorMsg);
                    val e = HttpErrorDetail(HttpErrorDetail.SERVER_DATA_CODE_NOT_0, errorMsg)
                    val apiException =
                        ApiException(ApiException.TYPE_SERVER_DATA, e, result.code, result)
                    subscriber.onError(apiException)
                    subscriber.onComplete()
                }
            }
        }.observeOn(AndroidSchedulers.mainThread()).onErrorResumeNext { throwable ->
            Observable.create(ObservableOnSubscribe { subscriber ->
                if (throwable is ApiException) {
                    val ex = throwable
                    val error = ex.requestError
                    when (ex.type) {
                        ApiException.TYPE_HTTP -> if (HttpErrorDetail.HTTP_ERROR_410 == error.errorCode) {
                            //ToastAlone.showLong(R.string.ll_public_force_exit);
                            /*new Handler().postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                EventBus.getDefault().post(new ForceExitAppEvent());
                                                            }
                                                        }, 500);*/
                        } else if (HttpErrorDetail.HTTP_ERROR_BODY_NULL == error.errorCode) {
                            // 这个错误不给用户任何提示
                        } else if (HttpErrorDetail.HTTP_ERROR_401 == error.errorCode) {
                            //EventBus.getDefault().post(new ForceExitAppEvent(error.errorMsg));
                            subscriber.onComplete()
                            return@ObservableOnSubscribe
                        }
                        ApiException.TYPE_SERVER_DATA -> {}
                        else -> {}
                    }
                }
                subscriber.onError(throwable)
                subscriber.onComplete()
            })
        }
    } /*    private void saveLog(BaseResultBean resultBean)
    {
        if (!TextUtils.isEmpty(resultBean.logId))
        {
            LogBean logBean = new LogBean();
            logBean.setTimeStamp(System.currentTimeMillis());
            logBean.setLogInfo(TextUtils.isEmpty(resultBean.message) ? "" : resultBean.message);
            logBean.setLogID(resultBean.logId);
            logBean.setMonthDay(DateUtil.getDateStr(logBean.getTimeStamp(), "yyyy-MM-dd"));
            logBean.setHourMinute(DateUtil.getDateStr(logBean.getTimeStamp(), "HH:mm:ss"));
            if (DaoManager.getInstance().getUserDaoSession() != null)
            {
                //添加时清除所有超过24小时的日志记录
                DaoManager.getInstance().getUserDaoSession().getLogBeanDao().queryBuilder()
                        .where(LogBeanDao.Properties.TimeStamp.lt(System.currentTimeMillis() - 24 * 60 * 60 * 1000)).build().list().clear();
                DaoManager.getInstance().getUserDaoSession().getLogBeanDao().insertOrReplace(logBean);
            }
        }
    }*/
}