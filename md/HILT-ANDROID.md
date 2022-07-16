## Hilt 资料说明

### 一：开源地址

1：[Hilt-android](https://developer.android.google.cn/training/dependency-injection/hilt-android?hl=zh-cn)  
2：[Hilt-android_doc](https://dagger.dev/hilt/)
3：hilt简化了dagger在android中的使用过程，@Component 被简化掉，但是其支持的范围很局限，具体可见上面文档链接

### 二：使用说明

1：@HiltAndroidApp 必须要在Application中声明注解 2：@AndroidEntryPoint 必须要在AppCompatActivity及其类中声明注解
3：@InstallIn(SingletonComponent::class) 和 @Singleton 成对在 @Module 中使用  
4：@InstallIn(ActivityComponent::class) 和 @ActivityScoped 成对在 @Module 中使用  
5：@ActivityRetainedScoped 和 @ActivityScoped
都是Activity组件使用的，@ActivityRetainedScoped一般和LiveData用作生命周期的绑定  
6：@Binds 定义接口实例，首先定义接口A，然后定义实现接口的类B，在 @Module (需要是abstract) 需要 @Binds 定义对应的 abstract fun，返回值为定义的接口A,方法入参为定义的实现类B
    基于dagger定义多个接口实例时需要在@Module中定义 @Named("key") 且需要定义对应的方法，注意下例中的test 和 test2 方法不能一致

```
//基于dagger接口多实现方法
@InstallIn(ActivityComponent::class)
@Module
abstract class TestInterfaceModule {
    @Binds
    @Named("TestClass")
    abstract fun test(testClazz: TestClass): TestInterface

    @Binds
    @Named("TestClass2")
    abstract fun test2(testClazz: TestClass2): TestInterface
}
```


7:



