package com.example.tomasyb.tomasybandroid.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.tomasyb.baselib.rvadapter.wrapper.HeaderAndFooterWrapper;

import com.example.tomasyb.tomasybandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 高仿朋友圈
 */
public class CircleFriendsActivity extends AppCompatActivity {

    @BindView(R.id.friends_rv)
    RecyclerView mFriendsRv;

    private HeaderAndFooterWrapper mHeadWrape;
    public static final String girl = "https://raw.githubusercontent.com/sfsheng0322/GlideImageView/master/resources/girl.jpg";
    public static final String headlogo = "http://img3.imgtn.bdimg.com/it/u=524208507,12616758&fm=206&gp=0.jpg";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_friends);
        ButterKnife.bind(this);
    }





}
