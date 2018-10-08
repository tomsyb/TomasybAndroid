package com.example.tomasyb.tomasybandroid.ui.activitylifecycle;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomasyb.tomasybandroid.R;

/**
 * 右侧fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-10-8.20:34
 * @since JDK 1.8
 */

public class RightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_right,container,false);
        return view;
    }
}
