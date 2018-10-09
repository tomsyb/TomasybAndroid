package com.example.tomasyb.tomasybandroid.ui.activitylifecycle;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 左侧fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-10-8.20:34
 * @since JDK 1.8
 */

public class LeftFragment extends Fragment {
    Unbinder unbinder;

    /**
     * 为Fragment加载布局时调用。
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        LogUtils.e("执行-->onCreateView");
        View view = inflater.inflate(R.layout.fg_left, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Fragment和Activity建立关联的时候调用。
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.e("执行-->onAttach");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("执行-->onCreate");
    }

    /**
     * 当Activity中的onCreate方法执行完后调用。
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.e("执行-->onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.e("执行-->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("执行-->onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e("执行-->onPause");
    }

    /**
     * Fragment中的布局被移除时调用。
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        LogUtils.e("执行-->onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("执行-->onDestroy");
    }

    /**
     * Fragment和Activity解除关联的时候调用。
     */
    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.e("执行-->onDetach");
    }

    @OnClick(R.id.btn_creatfg)
    public void onViewClicked() {

    }
}
