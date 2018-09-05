package com.example.tomasyb.tomasybandroid.ui.mvpexample.contact;

import com.example.tomasyb.baselib.base.mvp.BasePresenter;
import com.example.tomasyb.baselib.base.mvp.BaseView;
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
    interface view extends BaseView{
        void setData(List<MvpUseBean.StoriesBean> dataList);
    }
    //p层
    interface presenter extends BasePresenter{
        // 获取数据
        void getData();
    }

}
