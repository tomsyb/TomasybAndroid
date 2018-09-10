package com.example.tomasyb.baselib.base.mvp;

import io.reactivex.disposables.Disposable;

/**
 * Presenter基类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-5-30.12:45
 * @since JDK 1.8
 */

public interface IBasePresenter {
    void start();
    void detach();
    void addDisposable(Disposable disposable);
    void unDisposable();
}
