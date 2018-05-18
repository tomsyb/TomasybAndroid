package com.example.tomasyb.tomasybandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tomasyb.tomasybandroid.example.dagger.DaggerStudyActivity;
import com.example.tomasyb.utilslib.utils.ActivityUtils;
import com.tomasyb.rxjavalibs.RxjavaMainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_rv)
    RecyclerView mainRv;
    private List<String> mDatas = new ArrayList<>();
    private BaseQuickAdapter<String,BaseViewHolder> mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }
    private void initView() {
        mainRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_ac_main,mDatas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.item_ac_main_btn,item);
                helper.addOnClickListener(R.id.item_ac_main_btn);
            }
        };

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0://dagger2学习
                        ActivityUtils.startActivity(DaggerStudyActivity.class);
                        break;
                    case 1:
                        ActivityUtils.startActivity(RxjavaMainActivity.class);
                        break;
                }
            }
        });
        mainRv.setAdapter(mAdapter);

    }

    private void initData() {
        String[] stringArray = getResources().getStringArray(R.array.mainArray);
        for (int i = 0; i < stringArray.length; i++) {
            mDatas.add(stringArray[i]);
        }
        mAdapter.notifyDataSetChanged();
    }

}
