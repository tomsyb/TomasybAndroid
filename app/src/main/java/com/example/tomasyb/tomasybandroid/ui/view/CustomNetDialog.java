package com.example.tomasyb.tomasybandroid.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义view
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-10-30.14:12
 * @since JDK 1.8
 */

public class CustomNetDialog extends View{
    /**
     * 代码里new的时候调用
     * @param context
     */
    public CustomNetDialog(Context context) {
        super(context);
    }

    /**
     * xml布局中声明自动调用此方法
     * @param context
     * @param attrs 自定义属性是从这传进来的
     */
    public CustomNetDialog(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 不会主动调用，一般在第二个构造函数里主动调用，如View有style属性时
     * 4个构造方法和这个3个构造方法一样4个构造方法在API21之后才使用
     * @param context
     * @param attrs
     * @param defStyleAttr
     *
     */
    public CustomNetDialog(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 测量View的大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
