package com.example.tomasyb.tomasybandroid.ui;

import android.os.Bundle;
import android.view.View;

import com.example.tomasyb.baselib.base.BaseActivity;
import com.example.tomasyb.baselib.net.converter.GsonConverterFactory;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.LoginUser;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.net.ApiService;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitUseActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_retrofit_use;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }




    @OnClick({R.id.btn_1, R.id.btn_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                getUserMsg();
                break;
            case R.id.btn_2:
                break;
        }
    }

    /**
     * 获取用户信息
     */
    private void getUserMsg() {
        // 1、获取Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                //baseurl要以/结束，@Url：不要以/开头
                .baseUrl("http://scrs.daqsoft.com/api/")
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<LoginUser> call = apiService.getUserMsg("yanb", "123456");
        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                LoginUser body = response.body();
                LogUtils.e(body.getMessage());
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                LogUtils.e("");
            }
        });
        //取消请求
        //call.cancel();
    }
}
