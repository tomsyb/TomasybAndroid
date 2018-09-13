package com.example.tomasyb.tomasybandroid.ui.main.fragment;

import android.app.Activity;
import android.graphics.Bitmap;

import com.example.tomasyb.baselib.base.mvp.BaseFragment;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.tomasybandroid.R;
import butterknife.OnClick;


/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class MeFragment extends BaseFragment {


    @Override
    protected int getLayoutResource() {
        return R.layout.fg_main_me;
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }


    @Override
    protected void initView() {

    }

    public static Bitmap capture(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }



    @OnClick(R.id.btn_addresslist)
    public void onViewClicked() {

    }

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
