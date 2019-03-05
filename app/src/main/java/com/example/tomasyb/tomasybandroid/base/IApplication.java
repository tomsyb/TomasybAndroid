package com.example.tomasyb.tomasybandroid.base;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.greendao.DaoMaster;
import com.example.greendao.DaoSession;
import com.example.tomasyb.baselib.base.app.BaseApplication;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Tomasyb on 2018-4-24.
 * IApplication程序入口
 */

public class IApplication extends BaseApplication{
    private boolean isDebug = true;
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        initRouter();
        initGreenDao();
    }

    /**
     * 数据库
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "yanb.db");
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession(){
        return daoSession;
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
