package com.work.theIsle.javaMore;

import java.lang.Comparable;
/**
 * @Author TIKOU
 * @Date 2022/7/27-21:34
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
public interface Plate<T extends Comparable<T>> {
    void set(T t);

    T get();
}
