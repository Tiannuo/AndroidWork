package com.work.theIsle

import java.util.ArrayList

/**
 * @Author Administrator
 * @Date 2022/6/1-22:40
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
class TypeTest1 {
    abstract inner class Food {
        abstract fun name(): String
    }

    inner class Meat : Food() {
        override fun name(): String {
            return "Meat"
        }
    }

    open inner class Fruit : Food() {
        override fun name(): String {
            return "Fruit"
        }
    }

    open inner class Apple : Fruit() {
        override fun name(): String {
            return "Apple"
        }
    }

    inner class Banana : Fruit() {
        override fun name(): String {
            return "Banana"
        }
    }

/*    fun baseTest() {
        val foods: List<Food> =
        // 初始化再存放数据时会报错
        // foods.add(new Fruit());  报错
        // foods.add(new Apple());  报错
        // foods.add(new Meat());   报错
        val obj: Any = foods[0] //默认返回的是Object实例
        val fruit = foods[0] as Fruit //当需要指定类型的时候需要进行强转，但是强转就可能会带来类型转换异常的错误（get(1)是Apple）
    }

    fun baseTest2() {
        val foods: List<Food> = object : ArrayList<Food?>() {
            init {
                add(object : Food() {
                    override fun name(): String {
                        return "Food"
                    }
                })
                add(Fruit())
                add(Apple())
                add(Meat())
            }
        }
        // 初始化再存放数据时会报错
        // foods.add(new Fruit());  报错
        // foods.add(new Apple());  报错
        // foods.add(new Meat());   报错
        val obj: Any = foods[0] //默认返回的是Object实例
        val fruit = foods[0] as Fruit //当需要指定类型的时候需要进行强转，但是强转就可能会带来类型转换异常的错误（get(1)是Apple）
    }*/

    /**
     * = 左边是下定义，右边是申明实例化
     */
/*    fun test() {
        // 上界通配符 extends  泛型中类只能是当前类及其子类
        // 只能输出返回取值，不能在实例化后再存放，初始话需要放在申明实例化的时候Class{{}}
        // 如下定义的时候使用？extends Fruit ,上限是Fruit ，但是申明实例化的时候使用其子类Apple，则此时只能存放Apple（实例化泛型及其子类对象）
        // 简而言之，申明实例化时候泛型受限于下定义时候的类型，申明实例化对象存放受限于实例化的泛型
        // kt 中的out协变
        val fruits: List<Fruit> = object : ArrayList<Apple?>() {
            init {

                //add(new Food()) 报错
                //add(new Fruit()); // 报错
                add(Apple()) // Apple及其子类都可以放进去
            }
        }
        //fruits.add(new Fruit());
        //fruits.add(new Apple());
        //fruits.add(new Food());
        val fruit = fruits[0] as Apple
        val data: MutableList<in Fruit> = ArrayList<Food>()
        data.add(Apple())
        data.add(Fruit())
        val apples: MutableList<in Apple> = ArrayList()
        //apples.add(new Fruit());
        apples.add(Apple())
        object : Apple() {
        }
        //上界通配符 只能输入参数值， 泛型类是当期类及其父类
        // 可以获取值但是返回的OBJECT 需要强转 kt 中的in 逆变
        val foods: MutableList<in Fruit> = ArrayList<Food>()
        foods.add(Apple())
        //foods.add(new Food());
        foods.add(Banana())
        foods.add(Fruit())
        val apple = foods[0] as Apple
        val `object`: Any = foods[0]
        val efoods: List<Fruit> = ArrayList()
        //efoods.add(new Fruit());
        //https://blog.csdn.net/qq_33561055/article/details/87289812
        //https://zhuanlan.zhihu.com/p/249187830
    }*/
}