package com.example.tomasyb.tomasybandroid.ui.collect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tomasyb.tomasybandroid.R;

import java.util.List;

/**
 *
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-8-10.15:28
 * @since JDK 1.8
 */

public class CollectGridAdapter extends RecyclerView.Adapter<CollectGridAdapter.ViewHolder>{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mImgList;
    private int selectMax = 4;//最大的数量

    // 布局类型
    private static  final int TYPE_ADD = 1;
    private static  final int TYPE_IMG = 2;

    public CollectGridAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_grid_filter_img, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImg;
        LinearLayout mLl_del;//删除
        public ViewHolder(View view) {
            super(view);
            mImg = (ImageView)view.findViewById(R.id.fiv);
            mLl_del = (LinearLayout)view.findViewById(R.id.ll_del);
        }
    }


}
