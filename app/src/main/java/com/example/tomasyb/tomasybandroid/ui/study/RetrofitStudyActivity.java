package com.example.tomasyb.tomasybandroid.ui.study;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.base.BaseActivity;
import com.example.tomasyb.baselib.net.common.DefaultObserver;
import com.example.tomasyb.baselib.net.common.ProgressUtils;
import com.example.tomasyb.baselib.net.entity.BaseResponse;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.util.ToastUitl;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.LoginUser;
import com.example.tomasyb.tomasybandroid.common.Constant;
import com.example.tomasyb.tomasybandroid.net.ApiService;
import com.example.tomasyb.tomasybandroid.net.BaseEnty;
import com.example.tomasyb.tomasybandroid.net.RetrofitHelper;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Route(path = "/study/RetrofitStudyActivity")
public class RetrofitStudyActivity extends BaseActivity {

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
                RJget();
                break;
        }
    }

    /**
     * --------------------------------------------------------Rxjava和Retrofit结合使用
     */
    private void RJget() {
        RetrofitHelper.getmApiService()
                .getUser("yanb","123456")
                .compose(this.<BaseResponse<LoginUser>>bindToLifecycle())
                .compose(ProgressUtils.<BaseResponse<LoginUser>>applyProgressBar(this))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BaseResponse<LoginUser>>() {
                    @Override
                    public void onSuccess(BaseResponse<LoginUser> response) {
                        ToastUitl.showLong("请求成功"+response.getData().getName());
                    }
                });
    }

    /**
     * 获取用户信息http://scrs.daqsoft.com/api/rest/app/disguiseLogin?account=yanb&password=123456
     */
    private void getUserMsg() {

        // 1、获取Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //baseurl要以/结束，@Url：不要以/开头
                .baseUrl("http://scrs.daqsoft.com/api/")
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<BaseEnty<LoginUser>> call = apiService.getUserMsg("yanb", "123456");
        call.enqueue(new Callback<BaseEnty<LoginUser>>() {
            @Override
            public void onResponse(Call<BaseEnty<LoginUser>> call, Response<BaseEnty<LoginUser>> response) {
                ToastUitl.showLong("请求成功message="+response.body().getMessage());
            }

            @Override
            public void onFailure(Call<BaseEnty<LoginUser>> call, Throwable t) {

            }

        });
        //取消请求
        //call.cancel();
    }
}
