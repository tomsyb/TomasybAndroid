package com.example.tomasyb.tomasybandroid.example.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.base.IApplication;
import com.example.tomasyb.tomasybandroid.example.dagger.di.component.AppComponent;
import com.example.tomasyb.tomasybandroid.example.dagger.di.component.DaggerUserComponent;
import com.example.tomasyb.tomasybandroid.example.dagger.di.module.UserModule;
import com.example.tomasyb.tomasybandroid.example.dagger.mvp.contract.UserContract;
import com.example.tomasyb.tomasybandroid.example.dagger.mvp.presenter.UserPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class DaggerStudyActivity extends AppCompatActivity implements UserContract.View {
    @BindView(R.id.btn_changetv)
    Button btnChangetv;
    @BindView(R.id.tv_name)
    TextView tvName;

    //P层
    @Inject
    UserPresenter mPresenter;
    @Inject
    OkHttpClient client;
    @Inject
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_study);
        ButterKnife.bind(this);
        //把UserComponent 关联的AppComponent注入到本Activiity中
        AppComponent appComponent = ((IApplication) getApplication()).getAppComponent();
        //注入前先编译
        DaggerUserComponent
                .builder()
                .appComponent(appComponent)
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
