package com.example.tomasyb.tomasybandroid.ui.mvpexample.contact;

import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.base.mvp.IBaseView;
import com.example.tomasyb.tomasybandroid.ui.mvpexample.bean.MvpUseBean;

import java.util.List;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-9-4.17:39
 * @since JDK 1.8
 */

public interface MvpContact {
    //view
    interface view extends IBaseView {
        void setData(List<MvpUseBean.StoriesBean> dataList);
    }
    //p层
    interface presenter extends IBasePresenter {
        // 获取数据
        void getData();
    }

}
