package com.example.tomasyb.tomasybandroid.example.callback;

/**
 * Created by Tomasyb on 2018-4-27.
 */

public class Print {
    public interface onListener{
        void success(String name);
    }

    //定义变量纯数据
    private onListener mOnlistener;

    /**
     * 提供公共方法并且初始化接口类型数据
     * @param listener
     */
    public void setListener(onListener listener){
        this.mOnlistener = listener;
    }

    public void getName(String name){
        mOnlistener.success(name);
    }

}
