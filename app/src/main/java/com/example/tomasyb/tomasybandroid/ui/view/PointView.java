package com.example.tomasyb.tomasybandroid.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
        canvas.drawPoint(200,200,mPaint);
        canvas.drawPoints(new float[]{500,500,500,600,500,700},mPaint);//一组点
        /**
         * 绘制线条
         */
        canvas.drawLine(300,300,500,500,mPaint);//在坐标(300,300)(500,600)之间绘制一条直线
        canvas.drawLines(new float[]{100,200,200,200,
                100,300,200,300},mPaint);
    }

    private void init() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }
}
