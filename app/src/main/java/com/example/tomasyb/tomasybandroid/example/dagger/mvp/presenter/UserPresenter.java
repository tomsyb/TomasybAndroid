package com.example.tomasyb.tomasybandroid.example.dagger.mvp.presenter;

import com.example.tomasyb.tomasybandroid.example.dagger.DaggerStudyActivity;
import com.example.tomasyb.tomasybandroid.example.dagger.entity.User;

/**
 * Created by Tomasyb on 2018-4-24.
 */

public class UserPresenter {
    private DaggerStudyActivity activity;
    private User mUser;

    public UserPresenter(DaggerStudyActivity activity, User mUser) {
        this.activity = activity;
        this.mUser = mUser;
    }
    public void showUserName(){
        activity.showUserName(mUser.getName());
    }
}
