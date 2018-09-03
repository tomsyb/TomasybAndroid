package com.example.tomasyb.baselib.base.mvp;

/**
 * BaseView
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-9-3.17:18
 * @since JDK 1.8
 */

public interface BaseView {
    void showLoadingDialog(String msg);
    void dismissLoadingDialog();
}
