package com.example.tomasyb.tomasybandroid.example.dagger.di.component;

import com.example.tomasyb.baselib.di.scope.ActivityScope;
import com.example.tomasyb.tomasybandroid.example.dagger.DaggerStudyActivity;
import com.example.tomasyb.tomasybandroid.example.dagger.di.module.UserModule;

import dagger.Component;

/**
 * Created by Tomasyb on 2018-4-24.
 * 注入
 * dependencies = AppComponent.class Component之间可以依赖的
 * @ActivityScope 可以和Activity生命周期绑定
 */
@ActivityScope
@Component(modules = UserModule.class,dependencies = AppComponent.class)
public interface UserComponent {
    void inject(DaggerStudyActivity activity);
}
