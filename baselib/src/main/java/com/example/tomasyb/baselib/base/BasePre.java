package com.example.tomasyb.baselib.base;

import android.content.Context;

/**
 * Presenter基类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-5-30.12:45
 * @since JDK 1.8
 */

public abstract class BasePre<V,M>{
    public Context mContext;
    public V mView;
    public M mModel;

    /**
     * 设置View和Model
     * @param v view
     * @param m model
     */
    public void setVM(V v,M m){
        this.mView = v;
        this.mModel=m;
        this.onStart();
    }

    /**
     * 开始
     */
    public void onStart(){

    }

    /**
     * 结束
     */
    public void onDestroy(){

    }

}
