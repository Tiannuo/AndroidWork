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
注意：多控件或者等等在使用同一个LiveData时，数据的持久化，共享化需要保证observe 方法中的owner是同一个，否则不会数据同步。   
6：LiveData 优势  

&nbsp;&nbsp;&nbsp;a:确保ViewModel数据状态  
&nbsp;&nbsp;&nbsp;b:不会发生内存泄露 
&nbsp;&nbsp;&nbsp;c:不会因为Activity异常而崩溃
&nbsp;&nbsp;&nbsp;d:不需要手动处理生命周期  
&nbsp;&nbsp;&nbsp;e:数据保持最新状态  
&nbsp;&nbsp;&nbsp;f:可以在不同的Fragment之间共享资源，通讯方式改变  

7：DataBinding 布局文件参与页面渲染赋值，降低耦合度   DataBindingUtil.setContentView  
&nbsp;&nbsp;&nbsp;ViewBinding 布局加载器 ActivityJetpackBinding.inflate(layoutInflater)    setContentView(binding.root)
    
&nbsp;&nbsp;&nbsp;a:配置说明在build.gradle 中配置  

DataBinding 默认单向绑定，修改绑定的idol的数值，对应的布局控件数据也会发生改变；布局控件数据发生改变,idol的数值却不会发生改变
```
android {
     defaultConfig {
           // 旧版本
          dataBinding {
            enabled = true
         }
     }
     //viewBinding 一般是对布局控件的绑定，最新版本是dataBinding true
     buildFeatures {
        viewBinding true
        dataBinding true
    }
}
```
8：布局标签说明

```
<layout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable  // 一般是内容的提供元数据对象
            name="idol"
            type="com.work.theIsle.jetpack.bean.Idol" />
            
        <import/> // 一般api操作类对象
    </data>
```

8：二级布局  
include 标签 ，一级布局传值需要使用app:"@{  }"属性

9：@BindingAdapter  其主要作用是进一步在自定义的方法中完善布局文件中的代码需求

@BindingAdapter("image")必须使用注解在静态方法上，同时kotlin中使用需要@JvmStatic用于伴生对象的静态函数生成

10：动态绑定，双向绑定数据的两种方式  

&nbsp;&nbsp;&nbsp;第一种方式: @get:Bindable 数据双向绑定 ，外包装类需要继承BaseObservable，然后观察的数据 @get:Bindable 增加此注解，同时set方法中notifyPropertyChanged(BR.userName) 需要手动通知数据更新
```
class UserObservable : BaseObservable() {
    private val user: User = User("Jack")

    @get:Bindable
    var userName: String?
        get() = user.name
        set(userName) {
            if (!TextUtils.isEmpty(userName) && !TextUtils.equals(userName, user.name)) {
                user.name = userName!!
                LoggerUtils.i(userName)
                notifyPropertyChanged(BR.userName)
            }
        }

}
```
&nbsp;&nbsp;&nbsp;第二种方式（简洁）:  类似于LiveData 包装一下需要使用的对象，然后重写userName的get()和set(name) 方法userObservableFiled.get() 重新赋值
```
class UserObservableFiled {
private val userObservableFiled: ObservableField<User> = ObservableField()

    init {
        userObservableFiled.set(User("Rose"))
    }

    var userName: String?
        get() = userObservableFiled.get()!!.name
        set(name) {
            userObservableFiled.get()!!.name = name!!
            LoggerUtils.i("UserObservableFiled $name")
        }
}
```
11： DataBindingUtil.inflate 在RecyclerView.ViewHolder中使用 在ViewHolder中使用定义Binding属性用于和itemLayout互动赋值