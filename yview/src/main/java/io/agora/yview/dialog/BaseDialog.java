package io.agora.yview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import io.agora.yview.R;

/**
 * 基础弹框通过自己写布局来实现
 * 用法如下：
 * 中间弹出
 * dialog = new BaseDialog(this);
 * dialog.contentView(R.layout.dialog_center)
 * .canceledOnTouchOutside(true).show();
 * dialog.findViewById(R.id.tv_confirm).setOnClickListener(this);
 * dialog.findViewById(R.id.tv_cancel).setOnClickListener(this);
 *
 * 左侧：
 *   BaseDialog dialog_left = new BaseDialog(this);

 dialog_left.contentView(R.layout.dialog_left)
 .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT))
 .dimAmount(0.5f)
 .gravity(Gravity.LEFT | Gravity.CENTER)
 .animType(BaseDialog.AnimInType.LEFT)
 .canceledOnTouchOutside(true).show();

 上部：
 BaseDialog dialog_top = new BaseDialog(this);

 dialog_top.contentView(R.layout.dialog_photo)
 .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
 .dimAmount(0.5f)
 .gravity(Gravity.TOP)
 .offset(0, ScreenUtils.dpInt2px(this, 48))
 .animType(BaseDialog.AnimInType.TOP)
 .canceledOnTouchOutside(true).show();

 右边：
 BaseDialog dialog_right = new BaseDialog(this);

 dialog_right.contentView(R.layout.dialog_right)
 .layoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT))

 .gravity(Gravity.RIGHT | Gravity.CENTER)
 .animType(BaseDialog.AnimInType.RIGHT)
 .offset(20, 0)
 .canceledOnTouchOutside(true).show();

 底部：
 BaseDialog dialog_bottom = new BaseDialog(this);

 dialog_bottom.contentView(R.layout.dialog_photo)
 .gravity(Gravity.BOTTOM)
 .animType(BaseDialog.AnimInType.BOTTOM)
 .canceledOnTouchOutside(true).show();
 */
public class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        this(context, 0);

    }


    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);

        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除对话框的标题
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(0x00000000);
        getWindow().setBackgroundDrawable(gradientDrawable);//设置对话框边框背景,必须在代码中设置对话框背景，不然对话框背景是黑色的

        dimAmount(0.2f);
    }

    public BaseDialog contentView(@LayoutRes int layoutResID) {
        getWindow().setContentView(layoutResID);
        return this;
    }


    public BaseDialog contentView(@NonNull View view) {
        getWindow().setContentView(view);
        return this;
    }

    public BaseDialog contentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params) {
        getWindow().setContentView(view, params);
        return this;
    }
    public BaseDialog layoutParams(@Nullable ViewGroup.LayoutParams params) {
        getWindow().setLayout(params.width, params.height);
        return this;
    }


    /**
     * 点击外面是否能dissmiss
     *
     * @param canceledOnTouchOutside
     * @return
     */
    public BaseDialog canceledOnTouchOutside(boolean canceledOnTouchOutside) {
        setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

    /**
     * 位置
     *
     * @param gravity
     * @return
     */
    public BaseDialog gravity(int gravity) {

        getWindow().setGravity(gravity);

        return this;

    }

    /**
     * 偏移
     *
     * @param x
     * @param y
     * @return
     */
    public BaseDialog offset(int x, int y) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.x = x;
        layoutParams.y = y;

        return this;
    }

    /*
       设置背景阴影,必须setContentView之后调用才生效
        */
    public BaseDialog dimAmount(float dimAmount) {

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = dimAmount;
        return this;
    }


    /*
   动画类型
    */
    public BaseDialog animType(BaseDialog.AnimInType animInType) {


        switch (animInType.getIntType()) {
            case 0:
                getWindow().setWindowAnimations(R.style.dialog_zoom);

                break;
            case 1:
                getWindow().setWindowAnimations(R.style.dialog_anim_left);

                break;
            case 2:
                getWindow().setWindowAnimations(R.style.dialog_anim_top);

                break;
            case 3:
                getWindow().setWindowAnimations(R.style.dialog_anim_right);

                break;
            case 4:
                getWindow().setWindowAnimations(R.style.dialog_anim_bottom);

                break;
        }
        return this;
    }


    /*
    动画类型
     */
    public enum AnimInType {
        CENTER(0),
        LEFT(1),
        TOP(2),
        RIGHT(3),
        BOTTOM(4);

        AnimInType(int n) {
            intType = n;
        }

        final int intType;

        public int getIntType() {
            return intType;
        }
    }
}
