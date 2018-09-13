package com.example.tomasyb.tomasybandroid.ui.mvpexample.presenter;

import com.example.tomasyb.baselib.base.mvp.BasePresenter;
import com.example.tomasyb.baselib.base.retrofit.ExceptionHelper;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.bean.MvpUseBean;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.contact.MvpContact;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.net.Api;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * MvpUsePresenter
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-9-4.17:52
 * @since JDK 1.8
 */

public class MvpUsePresenter extends BasePresenter<MvpContact.view> implements MvpContact
        .presenter {


    public MvpUsePresenter(MvpContact.view view) {
        super(view);
    }

    @Override
    public void getData() {
//        Api.getInstance().getLogin("yinh","123456")
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        addDisposable(disposable);
//                        view.showLoadingDialog("正在加载中...");
//                    }
//                }).map(new Function<MvpUseBean, List<MvpUseBean.StoriesBean>>() {
//            @Override
//            public List<MvpUseBean.StoriesBean> apply(MvpUseBean mvpUseBean) throws Exception {
//                return mvpUseBean.getStories();
//            }
//        })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<MvpUseBean.StoriesBean>>() {
//                    @Override
//                    public void accept(List<MvpUseBean.StoriesBean> storiesBeans) throws Exception {
//                        view.dismissLoadingDialog();
//                        view.setData(storiesBeans);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        view.dismissLoadingDialog();
//                        ExceptionHelper.handleException(throwable);
//                    }
//                });
    }
}
