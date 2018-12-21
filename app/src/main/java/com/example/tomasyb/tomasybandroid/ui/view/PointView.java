package com.example.tomasyb.tomasybandroid.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义view之描绘点,直线
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-12-18.20:05
 * @since JDK 1.8
 */

public class PointView extends View{
    private Paint mPaint = new Paint();
    public PointView(Context context) {
        this(context,null);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);//画布颜色
        // 绘制点
        canvas.drawPoint(10,10,mPaint);
        canvas.drawPoints(new float[]{10,10,10,15,10,19},mPaint);//一组点
        /**
         * 绘制线条
         */
        canvas.drawLine(10,20,20,30,mPaint);//在坐标(10,20)(20,30)之间绘制一条直线
        canvas.drawLines(new float[]{10,20,30,40,
                10,30,40,50},mPaint);
        /**
         * 绘制矩形
         * 1、左上角，右下角点绘制
         * 2.Rect 方法Rect是int(整形)的
         * 3、RectF方法 而RectF是float(单精度浮点型)的
         * 23差别：两者最大的区别就是精度不同
         */
        canvas.drawRect(100,100,800,400,mPaint);
        Rect rect = new Rect(100,100,800,400);
        RectF rectF = new RectF(100,100,800,400);
        canvas.drawRect(rect,mPaint);
        canvas.drawRect(rectF,mPaint);

        /**
         * 绘制圆角矩形
         * canvas.drawRoundRect(100,100,800,400,30,30,mPaint);API21的时候才添加上以下一种方式为好
         */
        RectF rectF1 = new RectF(100,20,200,400);
        canvas.drawRoundRect(rectF1,30,30,mPaint);

    }

    private void init() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2f);
    }
}
