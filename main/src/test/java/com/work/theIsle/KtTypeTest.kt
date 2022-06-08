package com.work.theIsle

import java.math.BigDecimal

/**

 * @Author Administrator
 * @Date 2022/6/2-16:39
 * @Email wangweitikou1994@gmail.com
 * @Des
 */

abstract class FoodFather {

}

abstract class Food : FoodFather() {
    abstract fun name(): String
}

class Meat : Food() {
    override fun name(): String = "Meat"
}

class Fruit : Food() {
    override fun name(): String = "Fruit"
}

class Apple : Food() {
    override fun name(): String = "Apple"
}

class Banana : Food() {
    override fun name(): String = "Banana"
}


interface Plate<T> {
    fun set(t: T)
    fun get(): T
}

interface PlateOut<out T> {
    fun get(): T
}

open class PlateIn<in T> {
    fun set(t: T) {}
}

class ApplePlateIn<Apple> : PlateIn<Apple>() {

}

class FruitPlateIn<Fruit> : PlateIn<Fruit>() {

}

class FoodPlateIn<Food> : PlateIn<Food>() {

}

fun testP() {

}

public fun test() {
    val foods: MutableList<out Food> =
        mutableListOf<Food>(Apple(), Banana(), Fruit(), object : Food() {
            override fun name(): String {
                return "Food"
            }
        })

    /**
     * Type mismatch. Required:Apple Found:Food
     * 报错 由于Food是不变，所以不能作为输出值返回，即<font color= red> 实例化时 </font> 的 Apple()不能返回
     * 在Java中我们可以使用 ？extends Food 的方法进行上界的确认解决报错在Kotlin中应该如何解决？
     * 需要使用out T 协变 将Food修改为out Food
     */
    //val foods: MutableList<Food> = mutableListOf<Apple>(Apple()) 报错
    val outFoods: MutableList<out Food> = mutableListOf<Apple>(Apple())
    // 你以为到此就完结了？当然没有，发现outApples中的 <Apple> 并不是高亮的，让我们看如下代码
    /* mutableListOf 源码 <T> 为不变，所以就会导致和？extends Food 出现不用的结果
     public fun <T> mutableListOf1(vararg elements: T): MutableList<T> =
         if (elements.size == 0) ArrayList() else ArrayList(ArrayAsCollection(elements, isVarargs = true))+
     */
    /**
     * 我们可以看到out对数据结果并没有影响Fruit 对 Apple() 还是爆红提示我们要求类型异常
     */
    //val outFruits: MutableList<out Fruit> = mutableListOf<Fruit>(Apple(), Fruit())
    //val fruits: MutableList<Fruit> = mutableListOf<Fruit>(Apple(), Fruit())

    /**
     * 骚东西来了
     */
    val inApples: MutableList<in Apple> = mutableListOf<Food>(Apple(), Banana())
    //val inApples2: MutableList<in Apple> = mutableListOf<Food>(Apple(), Banana(), Fruit(), Food(), FoodFather())
    val foods1: MutableList<Food> = mutableListOf<Food>(Apple(), Banana())


    //val outLightFruits: MutableList<out Fruit> = mutableListOf<Fruit>(Apple(), Food())
    //val outLightFruits1: MutableList<out Fruit> = mutableListOf<Fruit>(Apple(), Fruit())
    //val outFruits1: MutableList<Fruit> = mutableListOf<Fruit>(Apple(), Banana())
    //val outFruits3: MutableList<FoodFather> =mutableListOf<FoodFather>(Food(), Fruit(), Apple(), Banana())

    //val typeFruits:TypeMutableListOf<Fruit> = mutableListOf<>()


}


/**
 * @param T
 * 不变
 */
interface ProductionConsumer<T> {
    fun product(): T
    fun consume(t: T)
}

class AppleStore : Production<Apple> {
    override fun product(): Apple {
        return Apple()
    }
}

class BananaStore : Production<Banana> {
    override fun product(): Banana {
        return Banana()
    }
}

class FruitStore : Production<Fruit> {
    override fun product(): Fruit {
        return Fruit()
    }
}

class FoodStore : Production<Food> {
    override fun product(): Food {
        return Apple()
    }
}


/**
 * @param out T
 * 协变 泛型只能作为输出值返回，不能作为入参值输入
 */
interface Production<out T> {
    fun product(): T
    // fun consume(t: T) 报错
}

fun testProduct() {
    val foodStore: Production<Food> = FoodStore()
    val appleStore: Production<Food> = AppleStore()
    val fruitStore: Production<Food> = FruitStore()
    val food: Food = FoodStore().product()
}

/**
 * @param in T
 * 逆变 泛型只能作为入参值输入，不能作为输出值返回
 */
interface Consumer<in T> {
    fun consume(t: T)
    // fun product(): T 报错
}

fun testConsumer() {

}

