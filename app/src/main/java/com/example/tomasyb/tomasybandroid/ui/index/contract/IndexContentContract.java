package com.example.tomasyb.tomasybandroid.ui.index.contract;

import com.example.tomasyb.baselib.base.BasePre;
import com.example.tomasyb.baselib.base.IBaseModel;
import com.example.tomasyb.baselib.base.IBaseView;
import com.example.tomasyb.tomasybandroid.ui.main.contract.IndexContract;

/**
 * 首页内容Contract
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-7.15:05
 * @since JDK 1.8
 */

public interface IndexContentContract {
    interface Model extends IBaseModel{

    }
    interface View extends IBaseView{

    }
    abstract class Presenter extends BasePre<View,Model>{

    }
}
