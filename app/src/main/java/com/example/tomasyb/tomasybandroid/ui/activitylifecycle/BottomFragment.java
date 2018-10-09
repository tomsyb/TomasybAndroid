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

    @OnClick(R.id.btn_getdata)
    public void onViewClicked() {
        tvShowBottom.setText(getArguments().getString("fgname")+mTitle);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mTitle = ((IntenTtransmitActivity)activity).getTitles();
    }
}
