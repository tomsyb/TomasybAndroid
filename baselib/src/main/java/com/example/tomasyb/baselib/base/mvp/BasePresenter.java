package com.example.tomasyb.baselib.base.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * BasePresenter的实现类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-9-4.17:47
 * @since JDK 1.8
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter {
    protected V view;
    //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private CompositeDisposable mCompositeDisposable;
    public BasePresenter(V view){
        this.view = view;
        start();
    }

    @Override
    public void detach() {
        this.view = null;
        unDisposable();
    }

    @Override
    public void start() {

    }

    /**
     * 将Disposable添加
     *
     * @param subscription
     */
    @Override
    public void addDisposable(Disposable subscription) {
        //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    @Override
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

}
