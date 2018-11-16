package com.example.tomasyb.baselib.widget.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 个人中心Android沉浸式状态栏 + scrollView顶部伸缩 + actionBar渐变
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-11-16.18:16
 * @since JDK 1.8
 */

public class TranslucentScrollView extends ScrollView{
    /**
     * 重写构造方法3个
     */
    public TranslucentScrollView(Context context) {
        super(context);
    }

    public TranslucentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslucentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
