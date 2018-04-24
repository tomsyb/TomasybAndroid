package com.example.tomasyb.tomasybandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_rv)
    RecyclerView mainRv;
    private List<String> mDatas = new ArrayList<>();
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
        mainRv.setAdapter(new BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_ac_main,mDatas) {


            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.item_ac_main_btn,item);
                helper.setOnClickListener(R.id.item_ac_main_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
    }

    private void initData() {
        String[] stringArray = getResources().getStringArray(R.array.mainArray);
        for (int i = 0; i < stringArray.length; i++) {
            mDatas.add(stringArray[i]);
        }
    }

}
