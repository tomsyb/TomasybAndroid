package com.example.tomasyb.tomasybandroid.ui.activitylifecycle;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 通信
 */
public class IntenTtransmitActivity extends AppCompatActivity implements BottomFragment
        .FragmentListener {

    @BindView(R.id.tv_showdata)
    TextView tvShowdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inten_ttransmit);
        ButterKnife.bind(this);
        getData();
    }

    /**
     * 获取值
     */
    private void getData() {
        LogUtils.e("你获取的值是-->" + getIntent().getStringExtra("name"));
    }

    @OnClick({R.id.btn_loadfg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_loadfg:
                initfg();
                break;

        }
    }

    /**
     * 动态加载fragment
     * 1.获取到FragmentManager，在Activity中可以直接通过getFragmentManager得到。
     * 2.开启一个事务，通过调用beginTransaction方法开启。
     * 3.向容器内加入Fragment，一般使用replace方法实现，需要传入容器的id和Fragment的实例。
     * 4.提交事务，调用commit方法提交。
     */
    private void initfg() {
        Fragment bottomfg = new BottomFragment();
        // 演示Activity向fragment传值
        Bundle bundle = new Bundle();
        bundle.putString("fgname", "动态创建fragment带的数据");
        bottomfg.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fg_contentlayout, bottomfg).commit();
    }

    /**
     * 定义方法让fragment获取数据
     */
    public String getTitles() {
        return "Activity的getTitles方法!!";
    }

    @Override
    public void getfgData(String str) {
        tvShowdata.setText(str);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
