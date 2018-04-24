package com.example.tomasyb.tomasybandroid.example.dagger.di.component;

import com.example.tomasyb.tomasybandroid.example.dagger.DaggerStudyActivity;
import com.example.tomasyb.tomasybandroid.example.dagger.di.module.UserModule;

import dagger.Component;

/**
 * Created by Tomasyb on 2018-4-24.
 * 注入
 */
@Component(modules = UserModule.class)
public interface UserComponent {
    void inject(DaggerStudyActivity activity);
}
