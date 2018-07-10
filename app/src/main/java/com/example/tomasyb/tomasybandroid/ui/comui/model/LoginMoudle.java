package com.example.tomasyb.tomasybandroid.ui.comui.model;

import com.example.tomasyb.tomasybandroid.bean.LoginUser;
import com.example.tomasyb.tomasybandroid.net.BaseEnty;
import com.example.tomasyb.tomasybandroid.net.RetrofitHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录页面的Moudle
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-10.15:27
 * @since JDK 1.8
 */

public class LoginMoudle {
    public interface ILoginImpl{
        void Fail();
        void success(Object o);

    }
    private String username;
    private String psd;

    private void login (String username,String psd){
        RetrofitHelper.getmApiService().getUser(username,psd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<BaseEnty<LoginUser>>() {
                    @Override
                    public void accept(BaseEnty<LoginUser> loginUserBaseEnty) throws Exception {

                    }
                });
    }
}
