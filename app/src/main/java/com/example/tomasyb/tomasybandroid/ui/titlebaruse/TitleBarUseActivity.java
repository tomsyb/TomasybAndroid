package com.example.tomasyb.tomasybandroid.ui.titlebaruse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tomasyb.baselib.widget.titlebar.CommonTitleBar;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TitleBarUseActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    CommonTitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_bar_use);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBackgroundColor(getResources().getColor(R.color.main));
        titleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_RIGHT_TEXT){
                    titleBar.toggleStatusBarMode();//切换bar颜色
                }
            }
        });
    }
}
