## Jetpack 说明

### 一：开源地址

1：[Jetpack](https://developer.android.com/jetpack?gclid=EAIaIQobChMIpKzMqdX_-AIVdBXUAR1vLA9GEAAYASAAEgJ9EvD_BwE&gclsrc=aw.ds)
2：[data-binding](https://developer.android.com/topic/libraries/data-binding/expressions)

### 二：使用说明

1：Lifecycle,包含LifecycleObserver（观察者），LifecycleOwner（观察对象），通过需要观察的对象例如组件view，mvp中的presenter等等实现LifecycleObserver,然后被观察者（观察对象）一般情况下Activity，Service等已经实现LifecycleOwner含有生命周期的都可添加观察者对象实例即可  
2：ProcessLifecycleOwner
增加app进程的监听，用于判断app是否在前台运行，应用场景主要有发送消息通知，监听app使用时间等，原先实现当app退出前台发送一个正在运行的通知，进入app通知取消，用的方法是ActivityLifecycleCallbacks，统计Activity运行数量信息判断，而此处则可以用ProcessLifecycleOwner
秒出。  
3：ViewModel,数据持久化 初始化的两种方式，  
第一种 通过委托代理 如下

```
private val vm: MyViewModel by viewModels()
```

第二种 通过ViewModelProvider().get()

```
private lateinit var vm: MyViewModel


vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                 MyViewModel::class.java

```

4：AndroidViewModel 自带Context，这样可以减少内存泄露
5：LiveData 在ViewModel中数据发生变化通知界面更新，就是数据的动态绑定操作，  
通过在ViewModel中定义MutableLiveData，然后使用MutableLiveData.observe(LifecycleOwner owner,Observer<? super T> observer)通过observe绑定被观察者（当前Activity，绑定生命周期）和观察者（更新数据逻辑）

