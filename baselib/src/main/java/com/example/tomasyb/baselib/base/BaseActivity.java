package com.example.tomasyb.baselib.base;

import android.support.v7.app.AppCompatActivity;

import com.example.tomasyb.baselib.mvp.IPresenter;

/**
 * Created by Tomasyb on 2018-4-20.
 * 1.泛型传入Presenter
 */

public class BaseActivity <P extends IPresenter> extends AppCompatActivity{
}
