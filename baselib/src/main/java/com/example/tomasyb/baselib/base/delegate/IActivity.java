package com.example.tomasyb.baselib.base.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Tomasyb on 2018-4-20.
 * 每个Activity实现此类
 */

public interface IActivity {
    /**
     * 初始化 View, 如果saveInstanceState 返回 0,
     * 框架则不会调用Activity#setContentView(int)}
     */
    int initView(@Nullable Bundle saveInstanceState);

    /**
     * 初始化数据
     * @param saveInstanceState
     */
    void initData(@Nullable Bundle saveInstanceState);

    /**
     *
     * @return 这个Activity是否使用Fragment
     * 据此判断是否注册。FragmentManager.FragmentLifecycleCallbacks
     * 返回false 此Activity不需要绑定奶Fragment，你再在这个Activity绑定继承于BaseFragment
     * 的Fragment将不再起任何作用
     */
    boolean useFragment();
    //是否使用eventbus
    boolean useEventBus();

}
