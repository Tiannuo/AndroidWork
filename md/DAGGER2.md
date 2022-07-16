## Dagger2资料说明

### 一：开源地址

1：[dagger](https://github.com/google/dagger)

### 二：集成说明（基于Kotlin）

1：module的 build.gradle 应用插件 低版本

```
apply plugin: 'kotlin-kapt'
```

高版本

```
plugins {
    xxx
    xxx
    id 'kotlin-kapt'
}
```

2：module的 build.gradle 文件依赖如下

```
api "com.google.dagger:dagger:$androidBaseLib.version_dagger"
kapt "com.google.dagger:dagger-compiler:$androidBaseLib.version_dagger"
api "com.google.dagger:dagger-android:$androidBaseLib.version_dagger"
// if you use the support libraries
api "com.google.dagger:dagger-android-support:$androidBaseLib.version_dagger" 
kapt "com.google.dagger:dagger-android-processor:$androidBaseLib.version_dagger"
```

3：需要注意的是kapt在组件化开发时候用了多个module时候，每个需要使用到dagger注入的项目都需要增加

```
kapt "com.google.dagger:dagger-compiler:$androidBaseLib.version_dagger"
kapt "com.google.dagger:dagger-android-processor:$androidBaseLib.version_dagger"
```

### 三：使用说明

#### 1：@Component

组件：作用参考前端的Vue，用于集合处理各种资源的组件，@Component 需要注解定义在接口interface上如项目中的DaggerSingletonComponent;
@Component(modules = [HttpDataModule::class], dependencies = [PresenterComponent::class])
注解中有两个重要的参数，modules
见2说明，dependencies是指的是依赖的其他组件，如此设计的理由是组件下的module关联的资源类都是一一对应的，dagger不允许两个组件注入相同资源，所以通过组件依赖组件，再由其他组件依赖module即可，即组件A在注入一个Activity，其他携带此Activity需要的资源组件B如果想要再Activity中使用，则需要由A中@Component
dependencies =[B]
见：DaggerSingletonComponent

#### 2：@modules

模型：数据模型，用于提供数据类对象的注解，在@Component中定义，注解在@modules类上
见：HttpDataModule

#### 3：@Provides

内容提供者：暴露需要注入的对象，在@modules中定义，注解在方法或属性上
见：HttpDataModule

#### 3：@Singleton

单例模式：全局单例模式，每个@Component @Module @Provides所处一个全局变量，数据是共享的，只要有一个@Module 中的
@Provides被@Singleton注解，则@Component 需要增加@Singleton

#### 4：@Scope

作用域：注解要求等同于@Singleton，是对其的一个扩展，局部单例模式，dagger编译要求每个@Component是独立的作用域

#### 5：@Named

名称定义：当需要使用多个相同类型的对象注入时候分为主组件和子组件两种情况
主组件：只需要在@Module 中的相同属性上加@Named定义不同的key即可 子组件：@Component
定义的接口方法中加@Named定义不同的key，@Module加@Named定义不同的key

#### 6：Lazy

懒加载：使用的时候再初始化数据，节约空间,其返回的是局部单例对象

```
@Named("YoungUser")
@Inject
lateinit var userLazy: Lazy<User>
LoggerUtils.i("DaggerActivityCode user" + userLazy.get().hashCode())
```
```
局部单例原理进行转换成需要的对象
if (provider instanceof Lazy) {
@SuppressWarnings("unchecked")
final Lazy<T> lazy = (Lazy<T>) provider;
return lazy;
}
```


#### 7：Provider

懒加载：使用的时候再初始化数据，节约空间,其返回的是不同对象

```
@Named("YoungUser")
@Inject
lateinit var userProvide: Provider<User>
LoggerUtils.i("DaggerActivityCode user" + userProvide.get().hashCode())
```
```
重新new DoubleCheck 生成两个不同的DoubleCheck对象的属性  private volatile Provider<T> provider;
return new DoubleCheck<T>(checkNotNull(provider));
```


### 三：注意说明

1：在使用组件组合的时候，三：使用说明中的子组件@Component中写的不是injecActivity之类的注入代码，而是子组件定义一个返回其@Module持有对象的接口方法（返回值就是持有的那个对象类）
如下

```
@Component(modules = [ResourcesModule::class])
interface ResourcesComponent {
public fun provideResourcesObject(): ResourcesObject
}
```

2：@Subcomponent 组件组合的第二种方式，较少使用，效果等同于@Component(modules = [HttpDataModule::class], dependencies
= [PresenterComponent::class])
只是注入由@Subcomponent injectActivity 子组件完成注入，父组件中定义接口方法返回子组件类
