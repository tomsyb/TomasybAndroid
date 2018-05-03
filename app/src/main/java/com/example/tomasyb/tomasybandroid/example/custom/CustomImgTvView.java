package com.example.tomasyb.tomasybandroid.example.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.tomasyb.tomasybandroid.R;

/**
 * 自定义Veiw图片下有文字
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-5-3 16:15
 * @since JDK 1.8
 */
public class CustomImgTvView extends View {
    private String mTvText;//文字
    private int mTvColor;//文字的颜色
    private int mTvSize;//打小
    private Bitmap mImg;//图片
    private int mImgScale;//图片类型

    private Rect mBounds;//控制绘制范围
    private Rect mTvBounds;//绘制字体的范围
    private Paint mPaint;//画笔

    public CustomImgTvView(Context context) {
        super(context);
    }

    public CustomImgTvView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImgTvView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //使用TypedArray获取自定义属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImgTvView, defStyleAttr, 0);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomImgTvView_TvText://文字
                    mTvText = a.getString(attr);
                    break;
                case R.styleable.CustomImgTvView_TvColor://文字的颜色(默认黑色)
                    mTvColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomImgTvView_TvSize://字体大小
                    mTvSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomImgTvView_Img://图片
                    mImg = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomImgTvView_ImgScaleType://图片类型
                    mImgScale = a.getInt(attr, 0);
                    break;
            }
        }
        a.recycle();
        //做一些初始化
        mBounds = new Rect();
        mTvBounds = new Rect();
        mPaint = new Paint();
        mPaint.setTextSize(mTvSize);
        //计算了描绘字体需要的范围
        mPaint.getTextBounds(mTvText,0,mTvText.length(),mTvBounds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //自己测量不需调用父类方法
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth = 0;
        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            Log.e("xxx", "EXACTLY");
            mWidth = specSize;
        } else {
            // 由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight() + mImg.getWidth();
            // 由字体决定的宽
            int desireByTitle = getPaddingLeft() + getPaddingRight() + mTvBounds.width();

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                int desire = Math.max(desireByImg, desireByTitle);
                mWidth = Math.min(desire, specSize);
                Log.e("xxx", "AT_MOST");
            }
        }
        /***
         * 设置高度
         */
        int mHeight = 0;
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mHeight = specSize;
        } else
        {
            int desire = getPaddingTop() + getPaddingBottom() + mImg.getHeight() + mTvBounds.height();
            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mHeight = Math.min(desire, specSize);
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}
