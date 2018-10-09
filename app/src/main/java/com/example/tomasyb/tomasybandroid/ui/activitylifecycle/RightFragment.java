package com.example.tomasyb.tomasybandroid.ui.activitylifecycle;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 右侧fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-10-8.20:34
 * @since JDK 1.8
 */

public class RightFragment extends Fragment {
    @BindView(R.id.tv_show)
    TextView tvShow;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_right, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 下面演示fragment与fragment之间的通信
     * getActivity()方法可以获取Activity相关联的控件
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = (Button) getActivity().findViewById(R.id.btn_getleftdata);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) getActivity().findViewById(R.id.btn_creatfg);
                ToastUtils.showLong(tv.getText());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
