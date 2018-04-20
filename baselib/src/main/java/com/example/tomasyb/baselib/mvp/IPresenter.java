package com.example.tomasyb.baselib.mvp;

/**
 * 框架要求框架中的每个 Presenter 都需要实现此类,以满足规范
 * Created by Tomasyb on 4/28/2016
 */
public interface IPresenter {

    /**
     * 做一些初始化操作
     */
    void onStart();

    /**
     * 在框架中Activity#onDestroy() 时会默认调用  IPresenter#onDestroy()
     */
    void onDestroy();
}
