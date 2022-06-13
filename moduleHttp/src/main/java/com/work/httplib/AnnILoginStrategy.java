package com.work.httplib;

import com.work.httplib.data.AnnRequestParams;

import io.reactivex.rxjava3.core.Observable;

/**
 *
 * @Author Administrator
 * @Date 2022/6/10-17:53
 * @Email wangweitikou1994@gmail.com
 * @Des AnnILoginStrategy 注解生成策略
 */

public interface AnnILoginStrategy
{
     Observable handleRequest(AnnRequestParams requestParams);
}
