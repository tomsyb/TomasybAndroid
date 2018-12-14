package com.example.tomasyb.baselib.widget.scrollview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.tomasyb.baselib.R;
import com.example.tomasyb.baselib.util.SizeUtils;

/**
 * 个人中心Android沉浸式状态栏 + scrollView顶部伸缩 + actionBar渐变
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-11-16.18:16
 * @since JDK 1.8
 */

public class TranslucentScrollView extends ScrollView {
    private final int GRADUAL_START = 50;//Y轴50dp渐变开始
    private final int GRADUAL_STOP = 300;// 300dp渐变结束
    private View mZoomView;// 伸缩视图
    private int mZoomView_InitHeight = 0;// 伸缩视图初始高度
    private View mTransView;// 渐变的视图
    private int transStartY = 50;// 渐变初始位置
    private int transEndY = 300;//渐变结束位置
    private int transColor = Color.WHITE;// 渐变颜色
    private Boolean isScaling = false;//是否正在放大
    private float mFirstPostion = 0;//记录首次按下位置

    /**
     * 透明度监听
     */
    private TranslucentChangeListener TransChangeListener;

    public void setTransChangeListener(TranslucentChangeListener transChangeListener) {
        TransChangeListener = transChangeListener;
    }

    public interface TranslucentChangeListener {
        /**
         * 透明度变化，0-255
         */
        void onTranslucentChanged(int transAlpha);
    }

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

    /**
     * 设置伸缩视图
     *
     * @param zoomView
     */
    public void setPullZoomView(View zoomView) {
        this.mZoomView = zoomView;
        mZoomView_InitHeight = zoomView.getLayoutParams().height;
        if (mZoomView_InitHeight == LayoutParams.MATCH_PARENT || mZoomView_InitHeight ==
                LayoutParams
                .WRAP_CONTENT) {
            zoomView.post(new Runnable() {
                @Override
                public void run() {
                    mZoomView_InitHeight = TranslucentScrollView.this.mZoomView.getHeight();
                }
            });
        }
    }

    /**
     * 设置渐变视图
     */
    public void setTransView(View transView) {
        setTransView(transView, getResources().getColor(R.color.colorPrimary), SizeUtils.dp2px
                (GRADUAL_START), SizeUtils.dp2px(GRADUAL_STOP));
    }

    /**
     * 设置渐变视图
     */
    public void setTransView(View transView, @ColorInt int transColor, int transStarY, int
            transEndY) {
        this.mTransView = transView;
        // 初始视图-透明
        this.mTransView.setBackgroundColor(ColorUtils.setAlphaComponent(transColor, 0));
        this.transStartY = transStarY;
        this.transEndY = transEndY;
        this.transColor = transColor;
        if (transStarY > transEndY) {
            throw new IllegalArgumentException("transStartY 不得大于 transEndY .. ");
        }
    }

    /**
     * 滚动监听
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int transAlpha = getTransAlpha();
        if (mTransView != null) {
            // 渐变视图透明度变化根据滑动的Y位置
            mTransView.setBackgroundColor(ColorUtils.setAlphaComponent(transColor, transAlpha));
        }
        if (TransChangeListener != null) {
            // 把透明度返回去
            TransChangeListener.onTranslucentChanged(transAlpha);
        }
    }

    /**
     * 这里就是当下拉的时候有形变操作
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mZoomView != null) {
            ViewGroup.LayoutParams params = mZoomView.getLayoutParams();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_UP:
                    // 手指离开后恢复图片
                    isScaling = false;
                    resetZoomView();
                    break;
                case MotionEvent.ACTION_MOVE:// 滑动
                    if (!isScaling) {
                        if (getScrollY() == 0) {//还没有滑动
                            mFirstPostion = ev.getY();
                        } else {
                            break;
                        }
                    }
                    int distance = (int) ((ev.getY() - mFirstPostion) * 0.6);
                    if (distance < 0) {
                        break;
                    }
                    isScaling = true;
                    params.height = mZoomView_InitHeight + distance;
                    mZoomView.setLayoutParams(params);
                    return true;
            }
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 重置ZoomView
     * 当下拉拉伸图片后
     */
    private void resetZoomView() {
        final ViewGroup.LayoutParams lp = mZoomView.getLayoutParams();
        final float height = mZoomView.getLayoutParams().height;// zoomview 当前高度
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F).setDuration(200);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                float cVal = (Float) animator.getAnimatedValue();
                lp.height = (int) (height - (height - mZoomView_InitHeight) * cVal);
                mZoomView.setLayoutParams(lp);
            }
        });
        anim.start();
    }

    /**
     * 获取透明度
     * 根据滑动的距离
     */
    private int getTransAlpha() {
        int scrollY = getScrollY();// 获取滑动距离
        if (transStartY != 0) {
            if (scrollY <= transStartY) {
                return 0;
            } else if (scrollY >= transEndY) {
                return 255;
            } else {
                return (int) ((scrollY - transStartY) / (transEndY - transStartY) * 255);
            }
        } else {
            if (scrollY >= transEndY) {
                return 255;
            }
            return (int) ((transEndY - scrollY) / transEndY * 255);
        }
    }
}
