package com.example.tomasyb.baselib.base.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Tomasyb on 2018-4-20.
 * 每个Activity实现此类
 * 1.
 */

public interface IActivity {
    //是否使用eventbus
    boolean useEventBus();

    /**
     * 初始化 View, 如果initView(Bundle)} 返回 0,
     * 框架则不会调用Activity#setContentView(int)}
     */
    void initView(@Nullable Bundle saveInstanceState);

    /**
     *
     * @param saveInstanceState
     */
    void initData(@Nullable Bundle saveInstanceState);

}
