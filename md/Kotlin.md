## Kotlin

### Kotlin 使用说明  
1：字符串比较，== 是检查两个字符串的字符是否相等（JAVA 中是equals），=== 是检查两个字符变量是否指向内存堆上的同一个对象（JAVA中== 检查），
2：infix 试用于单参的函数，使用infix时，接收者和函数之间的点操作和参数的括号均可省略，简洁 比如 to 函数

1: 对于get和set方法，kotlin内部已经实现了，如果还需要对其操作修改见案例   var userName: String? 下面写上 get() 和set(userName) {} 此种实现和vue很像
特别的当给set或者get方法提供注解的时候请使用 例如原本java中是 @Bindable而kotlin中@get:Bindable 利用get: 进行注解说明
```
public class UserObservable extends BaseObservable {
private User user;

    public UserObservable() {
        this.user = new User("Jack");
    }

    @Bindable
    public String getUserName() {
        return user.getName();
    }

    public void setUserName(String userName) {
        if (!TextUtils.isEmpty(userName) && !TextUtils.equals(userName, user.getName())) {
            user.setName(userName);
            LoggerUtils.INSTANCE.i(userName);
            notifyPropertyChanged(BR.userName);
        }
    }
}



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