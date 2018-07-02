package com.example.tomasyb.tomasybandroid.base;
import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tomasyb.baselib.base.BaseApplication;
import com.example.tomasyb.baselib.util.LogUtils;

/**
 * Created by Tomasyb on 2018-4-24.
 * IApplication程序入口
 */

public class IApplication extends BaseApplication{
    private boolean isDebug = true;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化logger
        LogUtils.logInit(true);
        initRouter();
    }

    /**
     * 初始化路由跳转
     */
    private void initRouter() {
        if (isDebug){// 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();// 打印日志
            ARouter.openDebug();// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

}
