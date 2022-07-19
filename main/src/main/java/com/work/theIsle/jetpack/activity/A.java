package com.work.theIsle.jetpack.activity;

import com.work.baselib.activity.BaseActivity;
import com.work.theIsle.dagger.User;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author TIKOU
 * @Date 2022/7/19-15:43
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
public class A extends BaseActivity {
    private Timer timer = new Timer();
    private TimerTask task;

    private User user;

    @Override
    public void initView() {
        task = new MyT(user) {
            @Override
            public void run() {

            }
        };

        timer.schedule(task,1000,1000);
    }

    @Override
    public void initData() {

    }

    class MyT extends TimerTask{

        private User user;

        public MyT(User user) {
            this.user = user;
        }

        @Override
        public void run() {

        }
    }
}
