package com.example.tomasyb.tomasybandroid.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 画布操作,包括画布的一些基本操作
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-12-19.15:32
 * @since JDK 1.8
 */

public class CanvasView extends View{
    private int mCanvasType = -1;
    private Paint mPaint = new Paint();
    private int mWidth,mHeight;
    public CanvasView(Context context) {
        super(context,null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCanvasType == -1)
            return;

        switch (mCanvasType){
            //位移
            case 0:
                // 将画布移动到100,100点
                mPaint.setColor(Color.RED);
                canvas.translate(200,200);
                canvas.drawCircle(0,0,100,mPaint);//原点这时候变成了100,100，画圆
                break;

            //缩放
            case 1:
                //canvas.translate(mWidth/2,mHeight/2);//画布中心移动到中心
                RectF rectF = new RectF(0,10,40,0);
                mPaint.setColor(Color.BLUE);
                canvas.drawRect(rectF,mPaint);
                // 进行缩放
                canvas.scale(0.1f,0.1f,2,0);
                mPaint.setColor(Color.RED);
                canvas.drawRect(rectF,mPaint);
                break;
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    private void init() {
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(1.0f);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setmCanvasType(int mCanvasType){
        this.mCanvasType  = mCanvasType;
        invalidate();//刷新
    }
}
