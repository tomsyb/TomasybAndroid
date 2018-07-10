package com.example.tomasyb.tomasybandroid.ui.study;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.base.BaseActivity;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.base.ToolbarBaseActivity;
import com.example.tomasyb.tomasybandroid.common.Constant;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Rxjava学习的页面
 */
@Route(path = "/study/RxjavaActivity")
public class RxjavaStudyActivity extends ToolbarBaseActivity {
    private int mPostion;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava_study;
    }

    @Override
    public void initView() {
        mPostion = getIntent().getIntExtra(Constant.STUDY_TYPE, 0);
    }

    @Override
    public void initPresenter() {

    }

    @OnClick(R.id.txjava_btn_show)
    public void onViewClicked() {
        matchData();
    }

    /**
     * 匹配数据
     */
    private void matchData() {
        switch (mPostion) {
            case 0:
                create();
                break;
            case 1:
                break;
        }
    }

    /**
     * 创建
     */
    private void create() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("创建");
            }
        });
    }

    @Override
    protected String getSubTitle() {
        return "Rxjava学习页面";
    }
}
