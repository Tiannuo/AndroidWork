package com.work.httplib.data;

import java.util.Map;

/**
 * @Author Administrator
 * @Date 2022/6/10-17:53
 * @Email wangweitikou1994@gmail.com
 * @Des RequestParams 接口方法参数
 * * 服务器接口定义
 * * 用法：请求参数和服务器保持一致
 **/
public class AnnRequestParams<T> {
    public static final String PARAMS = "format";
    public Map<String, T> paramMap;
    public String localMethodName;
}
