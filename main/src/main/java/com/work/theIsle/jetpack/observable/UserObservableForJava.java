package com.work.theIsle.jetpack.observable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.work.theIsle.BR;
import com.work.theIsle.jetpack.bean.User;

/**
 * @Author TIKOU
 * @Date 2022/7/20-18:42
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
public class UserObservableForJava extends BaseObservable {
    private User user;

    public UserObservableForJava() {
        user = new User("111");
    }

    @Bindable
    public String getUserName(){
        return user.getName();
    }

    public void setUserName(String name){
        user.setName(name);
        notifyPropertyChanged(BR.userName);
    }
}
