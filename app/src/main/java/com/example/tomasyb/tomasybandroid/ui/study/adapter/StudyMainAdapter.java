package com.example.tomasyb.tomasybandroid.ui.study.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.StudyMainEntity;

import java.util.List;

/**
 * Created by yanbo on 2018/7/8.
 * 学习页面的适配器
 */

public class StudyMainAdapter extends CommonRecycleViewAdapter<StudyMainEntity>{

    public StudyMainAdapter(Context context, int layoutId, List<StudyMainEntity> mDatass) {
        super(context, layoutId, mDatass);
    }

    @Override
    public void convert(ViewHolderHelper helper, StudyMainEntity bean) {
        helper.setText(R.id.normal_tv_title,bean.getTitle());
        helper.setText(R.id.item_content,bean.getContent());
    }
}
