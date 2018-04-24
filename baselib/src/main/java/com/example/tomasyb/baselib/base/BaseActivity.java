package com.example.tomasyb.baselib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tomasyb.baselib.base.delegate.IActivity;
import com.example.tomasyb.baselib.mvp.IPresenter;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Tomasyb on 2018-4-20.
 * 1.泛型传入Presenter
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity {
    //黄油刀
    private Unbinder mBinder;

    @Nullable
    private P mPresenter;//如果当前页面逻辑简单可以为null

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            int layoutId = initView(savedInstanceState);
            if (layoutId != 0) {//=0不会调用setContentView,也不会绑定butternife
                setContentView(layoutId);
                mBinder = ButterKnife.bind(this);//绑定注意取消绑定
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinder != null && mBinder != Unbinder.EMPTY) {
            mBinder.unbind();
            this.mBinder = null;
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
            this.mPresenter = null;
        }

    }
    //默认使用
    @Override
    public boolean useEventBus() {
        return true;
    }
    //默认使用
    @Override
    public boolean useFragment() {
        return true;
    }
}
