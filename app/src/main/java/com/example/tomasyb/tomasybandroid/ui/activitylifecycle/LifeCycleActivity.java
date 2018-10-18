package com.example.tomasyb.tomasybandroid.ui.activitylifecycle;

import android.content.Intent;
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
 * Activity生命周期
 */
public class LifeCycleActivity extends AppCompatActivity {

    @BindView(R.id.tv_showlifecycle)
    TextView mTvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        LogUtils.e("执行-->onCreate");
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.e("执行-->onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("执行-->onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e("执行-->onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("执行-->onDestroy");
    }

    /**
     * 保存当前用户信息
     * 存储当前Activity的状态信息，存储数据，重建之后恢复用
     * 重新恢复 调用onRestoreInstanceState
     * <p>
     * 异常比如内存紧张才调用onSaveInstanceState和onRestoreInstanceState方法
     *
     * @param outState Bundle对象
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.e("执行-->onSaveInstanceState");
    }

    /**
     * 异常出现，数据恢复调用
     * 此外也可在onCreate（）方法中恢复
     *
     * @param savedInstanceState 一定不为空可不用判断，但是onCreate（）中可为空必须判断
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.e("执行-->onRestoreInstanceState");
    }


    @OnClick({R.id.btn_startactivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_startactivity:
                Bundle bundle = new Bundle();
                bundle.putString("name","严博");
                Intent intent  = new Intent(this,IntenTtransmitActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

}
