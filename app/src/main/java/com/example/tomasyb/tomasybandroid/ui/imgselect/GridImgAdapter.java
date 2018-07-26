package com.example.tomasyb.tomasybandroid.ui.imgselect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片选择适配器
 * Recycleview来做，可以在这里学到有关Recycleview的知识
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-26.18:30
 * @since JDK 1.8
 */

public class GridImgAdapter extends RecyclerView.Adapter<GridImgAdapter.ViewHolder> {
    /**
     * Context
     */
    private Context mContent;
    private List<String> mImgList = new ArrayList<>();//图片数据
    private LayoutInflater mInflater;//布局解析器
    private int selectMax = 9;//图片最多选择
    public static final int TYPE_ADD = 1;//添加图片按钮
    public static final int TYPE_PICTURE = 2;//展示图片

    /**
     * 设置图片数据
     *
     * @param mImgList
     */
    public void setmImgList(List<String> mImgList) {
        this.mImgList = mImgList;
    }

    /**
     * 设置最大选取的图片数量
     *
     * @param selectMax
     */
    public void setSelectMax(int selectMax) {
        this.selectMax = selectMax;
    }

    /**
     * 构造器
     *
     * @param mContent
     */
    public GridImgAdapter(Context mContent) {
        this.mContent = mContent;
        mInflater = LayoutInflater.from(mContent);
    }

    /**
     * 建立自己的viewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        LinearLayout mLl_del;

        public ViewHolder(View view) {
            super(view);
            mImg = view.findViewById(R.id.item_imselect_img_fiv);
            mLl_del = view.findViewById(R.id.ll_del);
        }
    }
    /**
     * 控制是否显示加号图片
     */
    private boolean isShowAddItem(int postion){
        int size = mImgList.size() == 0?0:mImgList.size();
        return postion == size;
    }
    //-------------------------------------------------下面是父类方法

    /**
     * 创建viewholder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_img_select, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //少于8张，显示继续添加的图标
        holder.mLl_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = holder.getAdapterPosition();
                // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
                if (index !=RecyclerView.NO_POSITION){
                    mImgList.remove(index);
                    notifyItemRemoved(index);
                    notifyItemRangeChanged(index,mImgList.size());
                }
            }
        });
        Glide.with(holder.itemView.getContext())
                .load(mImgList.get(position))
                .into(holder.mImg);
    }

    /**
     * 布局item数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        //
        if (mImgList.size() < selectMax) {
            return mImgList.size() + 1;//添加加号图片
        } else {
            return mImgList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)){
            return TYPE_ADD;
        }else {
            return TYPE_PICTURE;
        }
    }
}
