package com.example.tomasyb.tomasybandroid.ui.main.index.holder;

import android.view.View;
import android.widget.ImageView;

import com.example.tomasyb.tomasybandroid.R;

import io.agora.yview.banner.holder.Holder;


/**
 * Created by Sai on 15/8/4.
 * 本地图片Holder例子
 */
public class LocalImageHolderView extends Holder<Integer> {
    private ImageView imageView;

    public LocalImageHolderView(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView =itemView.findViewById(R.id.ivPost);
    }

    @Override
    public void updateUI(Integer data) {
        imageView.setImageResource(data);
    }
}