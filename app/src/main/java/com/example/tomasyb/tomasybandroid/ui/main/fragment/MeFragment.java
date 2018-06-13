package com.example.tomasyb.tomasybandroid.ui.main.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.img)
    ImageView img;
    Unbinder unbinder;
    @BindView(R.id.start)
    Button start;

    @Override
    protected int getLayoutResource() {
        return R.layout.fg_main_me;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    public static Bitmap capture(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }



    @OnClick(R.id.start)
    public void onViewClicked() {
        Bitmap capture = capture(getActivity());

    }





}
