package com.work.theIsle;

import androidx.collection.ArrayMap;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Administrator
 * @Date 2022/6/1-22:40
 * @Email wangweitikou1994@gmail.com
 * @Des
 */
public class TypeTest {

    public abstract class Food {
        abstract String name();
    }

    public class Meat extends Food {
        @Override
        String name() {
            return "Meat";
        }
    }

    public class Fruit extends Food {
        @Override
        String name() {
            return "Fruit";
        }
    }

    public class Apple extends Fruit {
        @Override
        String name() {
            return "Apple";
        }
    }

    public class Banana extends Fruit {
        @Override
        String name() {
            return "Banana";
        }
    }

    public void baseTest4() {
        //下界通配符 只能输入参数值， 泛型类是当期类及其父类
        // 可以获取值但是返回的OBJECT 需要强转 kt 中的in 逆变
        List<? super Fruit> foods = new ArrayList<Food>() {{
            add(new Apple());
            Object object = get(0);
        }};
        foods.add(new Apple());
        // foods.add(new Food()); 报错
        foods.add(new Banana());
        foods.add(new Fruit());
        //foods.add(new Meat());报错
        Apple apple = (Apple) foods.get(0);
        Object object = foods.get(0);


    }

    public void test8() {
        List<? super Fruit> fruits = new ArrayList<Fruit>() {{
            //add(new Object()) 报错
            add(new Apple());
            add(new Fruit());
            add(new Banana());
            Object object = get(0);
        }};
        fruits.add(new Apple());
        fruits.add(new Banana());
    }

    public void test5() {
        List<? super Fruit> fruits = new ArrayList<Food>() {{
            //add(new Object()) 报错
            add(new Apple());
            add(new Food() {
                @Override
                String name() {
                    return null;
                }
            });
            add(new Meat());//正常添加 Meat和Fruit均为Food的子类，但此时的作用域却是<font color= red> 实例化时 </font>Food类型下，而不受<font color= red> 下定义时 </font> T类型限制
            add(new Fruit());
            Object object = get(0);
        }};
        // fruits = new ArrayList<Apple>(); 报错
        // fruits = new ArrayList<Banana>(); 报错
        fruits.add(new Apple());
        //fruits.add(new Food()); 报错
        fruits.add(new Banana());
        fruits.add(new Fruit());
        //fruits.add(new Meat());报错 <font color= red> 初始化后 </font> 作用域发生改变，添加对象受到<font color= red> 下定义时 </font> T类型限制
        Apple apple = (Apple) fruits.get(0);
        Object object = fruits.get(0);
    }

    public void baseTest() {
        List<? extends Food> foods = new ArrayList<Fruit>() {{
           /* add(new Food(){
                @Override
                String name() {
                    return "Food";
                }
            });*///报错
            add(new Fruit());
            add(new Apple());
            // add(new Meat());报错
        }};
        // 初始化再存放数据时会报错
        // foods.add(new Fruit());  报错
        // foods.add(new Apple());  报错
        // foods.add(new Meat());   报错
        Object obj = foods.get(0);//默认返回的是Object实例
        Fruit fruit = (Fruit) foods.get(0);//当需要指定类型的时候需要进行强转，但是强转就可能会带来类型转换异常的错误（get(1)是Apple）
    }

    public void baseTest2() {
        List<? extends Food> foods = new ArrayList<Food>() {{
            add(new Food() {
                @Override
                String name() {
                    return "Food";
                }
            });
            add(new Fruit());
            add(new Apple());
            add(new Meat());
        }};
        // 初始化再存放数据时会报错
        // foods.add(new Fruit());  报错
        // foods.add(new Apple());  报错
        // foods.add(new Meat());   报错
        Object obj = foods.get(0);//默认返回的是Object实例
        Fruit fruit = (Fruit) foods.get(0);//当需要指定类型的时候需要进行强转，但是强转就可能会带来类型转换异常的错误（get(1)是Apple）
    }

    /**
     * = 左边是下定义，右边是申明实例化
     */
    public void test() {
        // 上界通配符 extends  泛型中类只能是当前类及其子类
        // 只能输出返回取值，不能在实例化后再存放，初始话需要放在申明实例化的时候Class{{}}
        // 如下定义的时候使用？extends Fruit ,上限是Fruit ，但是申明实例化的时候使用其子类Apple，则此时只能存放Apple（实例化泛型及其子类对象）
        // 简而言之，申明实例化时候泛型受限于下定义时候的类型，申明实例化对象存放受限于实例化的泛型
        // kt 中的out协变
        List<? extends Fruit> fruits = new ArrayList<Apple>() {{

            //add(new Food()) 报错
            //add(new Fruit()); // 报错
            add(new Apple()); // Apple及其子类都可以放进去
        }};
        //fruits.add(new Fruit());
        //fruits.add(new Apple());
        //fruits.add(new Food());
        Apple fruit = (Apple) fruits.get(0);

        List<? super Fruit> data = new ArrayList<Food>();
        data.add(new Apple());
        data.add(new Fruit());

        List<? super Apple> apples = new ArrayList<>();
        //apples.add(new Fruit());
        apples.add(new Apple());

        new Apple() {{

        }};
        //下界通配符 只能输入参数值， 泛型类是当期类及其父类
        // 可以获取值但是返回的OBJECT 需要强转 kt 中的in 逆变
        List<? super Fruit> foods = new ArrayList<Food>() {{
            add(new Apple());
            Object object = get(0);
        }};
        foods.add(new Apple());
        // foods.add(new Food()); 报错
        foods.add(new Banana());
        foods.add(new Fruit());
        //foods.add(new Meat());报错
        Apple apple = (Apple) foods.get(0);
        Object object = foods.get(0);

        List<? extends Fruit> efoods = new ArrayList<>();
        //efoods.add(new Fruit());
        //https://blog.csdn.net/qq_33561055/article/details/87289812
        //https://zhuanlan.zhihu.com/p/249187830
    }

    @Test
    public void test22() {
        System.out.println(-6 >>> 3);
        int index = 2;
        index = ~index;
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put(null, "null0");
        arrayMap.put("name", "王伟");
        System.out.println("null= " + arrayMap.get(null));
        System.out.println("index= " + index);
    }
}


