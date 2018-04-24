package com.example.tomasyb.tomasybandroid.example.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.example.dagger.di.component.DaggerUserComponent;
import com.example.tomasyb.tomasybandroid.example.dagger.di.module.UserModule;
import com.example.tomasyb.tomasybandroid.example.dagger.mvp.contract.UserContract;
import com.example.tomasyb.tomasybandroid.example.dagger.mvp.presenter.UserPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaggerStudyActivity extends AppCompatActivity implements UserContract.View {
    @BindView(R.id.btn_changetv)
    Button btnChangetv;
    @BindView(R.id.tv_name)
    TextView tvName;

    //P层
    @Inject
    UserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_study);
        ButterKnife.bind(this);
        //注入前先编译
        DaggerUserComponent
                .builder()
                .userModule(new UserModule(this))
                .build()
                .inject(this);

    }

    @Override
    public void showUserName(String name) {
        tvName.setText(name);
    }

    @OnClick(R.id.btn_changetv)
    public void onViewClicked() {
        mPresenter.showUserName();
    }
}
