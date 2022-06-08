package com.work.theIsle

/**

 * @Author Administrator
 * @Date 2022/6/2-20:53
 * @Email wangweitikou1994@gmail.com
 * @Des
 */

interface TypeMutableList<E> : MutableList<E> {
    public fun <T> typeMutableListOf(vararg elements: T): MutableList<T>
}