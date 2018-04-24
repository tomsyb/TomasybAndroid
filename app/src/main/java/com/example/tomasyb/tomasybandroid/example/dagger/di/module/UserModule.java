package com.example.tomasyb.tomasybandroid.example.dagger.di.module;

import com.example.tomasyb.tomasybandroid.example.dagger.DaggerStudyActivity;
import com.example.tomasyb.tomasybandroid.example.dagger.entity.User;
import com.example.tomasyb.tomasybandroid.example.dagger.mvp.presenter.UserPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Tomasyb on 2018-4-24.
 * 提供依赖对象
 * 1.@Module标识
 * 2.构造方法传入Activity
 *
 * 注意：
 * @Provides注解的函数需要以provide开头
 */
@Module
public class UserModule {
    private DaggerStudyActivity activity;

    public UserModule(DaggerStudyActivity activity) {
        this.activity = activity;
    }
    /**
     * 提供对象
     */
    @Provides
    public DaggerStudyActivity provideDaggerStudyActivity(){
        return activity;
    }
    @Provides
    public User provideUser(){
        return new User("我是用户");
    }
    //参数是由上面提供
    @Provides
    public UserPresenter provideUserPresenter(DaggerStudyActivity activity,User user){
        return new UserPresenter(activity,user);
    }

}
