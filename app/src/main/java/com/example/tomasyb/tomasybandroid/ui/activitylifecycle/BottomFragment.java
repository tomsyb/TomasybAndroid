package com.example.tomasyb.tomasybandroid.ui.activitylifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 底部fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-10-9.20:03
 * @since JDK 1.8
 */

public class BottomFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.tv_show_bottom)
    TextView tvShowBottom;
    private String mTitle = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_bottom, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 当fragment被加载到activity的时候调用
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 通过强转成宿主Activity获取数据
        mTitle = ((IntenTtransmitActivity) activity).getTitles();
        if (activity instanceof FragmentListener) {
            listener = (FragmentListener) activity;
        } else {
            throw new IllegalArgumentException("activity 必须实现FragmentListener");
        }
    }

    //___________________________________________________________________________下面演示Activity
    // 调用fragment方法获取数据
    //定义用来与外部Activity交互，获取到宿主activity
    private FragmentListener listener;
    public interface FragmentListener {
        void getfgData(String str);
    }

    @OnClick({R.id.btn_getdata, R.id.btn_changedata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getdata:
                tvShowBottom.setText(getArguments().getString("fgname") + mTitle);
                break;
            case R.id.btn_changedata:
                listener.getfgData("我是接口");
                break;
        }
    }

    /**
     * 传递进来的activity对象释放掉避免内存泄漏
     */
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
