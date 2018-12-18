package com.example.tomasyb.tomasybandroid.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;

/**
 * 自定义饼状图view
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-10-30.14:12
 * @since JDK 1.8
 */

public class CustomPieChart extends View {
    // 此处颜色使用的是ARGB带Alpha通道
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000,
            0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private float mStartAngle = 0;//饼图初始角度
    private ArrayList<PieChart> mChartData;//数据
    private int mWidth, mHeight;
    private Paint mPaint = new Paint();//画笔

    /**
     * 一般在直接new 一个View的时候调用
     *
     * @param context
     */
    public CustomPieChart(Context context) {
        super(context, null);
    }

    /**
     * 一般在layout文件xml中使用的时候会调用
     *
     * @param context
     * @param attrs   关于它的所有属性(包括自定义属性)都会包含在attrs中传递进来。
     */
    public CustomPieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //this(context, attrs, R.attr.imageButtonStyle);//调用了三个参数的构造函数，明确指定第三个参数
        initPaint();//初始化一些东西
    }

    /**
     * 不会主动调用，一般在第二个构造函数里主动调用，如View有style属性时
     * 4个构造方法和这个3个构造方法一样4个构造方法在API21之后才使用
     *
     * @param context
     * @param attrs
     * @param defStyleAttr 这里的默认style是指它在当前Application或Activity所用的Theme中的默认Style且只有明确调用
     *                     (在两个构造方法中明确调用)的时候才会生效
     */
    public CustomPieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 测量View的大小
     * 参数其实不是宽高，而是由宽高各自方向上对应的测量模式来合成的一个值
     * 模式有3种UNSPECIFIED EXACTLY  AT_MOST
     * int widthmode = MeasureSpec.getMode(widthMeasureSpec);// 获取宽度的测量模式
     * int widthsize = MeasureSpec.getSize(widthMeasureSpec);// 获取宽度的确切数值
     * 高度同理
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**
     * 在视图大小发送改变时调用
     * 在测量完View并使用setMeasuredDimension函数之后View的大小基本已经确定，为什么还要再次确定View的大小呢
     * 因为View的大小不仅仅由View本身控制，而且受父控件的影响，所以我么在确定View大小的时候最好使用系统提供的onSizeChanged回调函数
     *
     * @param w    宽
     * @param h    高
     * @param oldw 上一次宽
     * @param oldh 上一次高
     *             只需关注 宽度(w), 高度(h) 即可，这两个参数就是View最终的大小
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /**
     * 确定子View的位置
     * 在自定义ViewGroup中用到，本方法一般是循环取出子View然后经过计算得出各个子View位置的坐标值，然后用以下函数设置子View位置。
     * child.layout(l, t, r, b);
     *
     * @param changed
     * @param left    View左侧距父View左侧的距离 对应的函数 ：getLeft()
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 实际绘制的部分 Canvas绘图
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mChartData)
            return;
        float mCurrentStartAngle = mStartAngle;//当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2);//将画布移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);//饼图半径
        RectF rect = new RectF(-r, -r, r, r);//饼图绘制区域
        for (int i = 0; i < mChartData.size(); i++) {
            PieChart pie = mChartData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rect, mCurrentStartAngle, pie.getAngle(), true, mPaint);
            mCurrentStartAngle += pie.getAngle();
        }

    }

    /**
     * 初始化画笔
     * 在构造方法中调用
     */
    private void initPaint() {
        /**
         * 设置画笔
         */
        mPaint.setStyle(Paint.Style.FILL);//画笔模式为填充
        mPaint.setAntiAlias(true);
    }

    /**
     * 设置起始角度
     */
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();//刷新
    }

    public void setData(ArrayList<PieChart> mChartData) {
        this.mChartData = mChartData;
        initData(mChartData);
        invalidate();//刷新 在更改了数据需要重绘界面时要调用invalidate()这个函数重新绘制。
    }

    /**
     * 初始化数据
     * @param mChartData
     */
    private void initData(ArrayList<PieChart> mChartData) {
        if (null == mChartData || mChartData.size() == 0)//数据有问题直接返回
            return;
        float sumValue = 0;
        for (int i = 0; i < mChartData.size(); i++) {
            PieChart pieChart = mChartData.get(i);
            sumValue += pieChart.getValue();//计算初始值和
            int j = i % mColors.length;
            pieChart.setColor(mColors[j]);
        }
        float sumAngle = 0;
        for (int i = 0; i < mChartData.size(); i++) {
            PieChart pieChart = mChartData.get(i);
            float percentage = pieChart.getValue() / sumValue;//百分比
            float angle = percentage * 360;//对应的角度
            pieChart.setPercentage(percentage);//记录百分比
            pieChart.setAngle(angle);//记录角度大小
            sumAngle += angle;
            LogUtils.e("角度" + pieChart.getAngle());
        }

    }
}
