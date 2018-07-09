package com.example.tomasyb.tomasybandroid.ui.index.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.bean.NewsSummary;

import java.util.List;


/**
 * 首页内容适配器
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-7.16:40
 * @since JDK 1.8
 */

public class IndexContentAdapter extends MultiItemRecycleViewAdapter<NewsSummary>{
    //不同类型
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_PHOTO_ITEM = 1;
    public IndexContentAdapter(Context context, List<NewsSummary> datas){
        super(context, datas, new MultiItemTypeSupport<NewsSummary>() {
            @Override
            public int getLayoutId(int itemType) {
                if (itemType == TYPE_PHOTO_ITEM){
                    return R.layout.item_index_content_photo;
                }else {
                    return R.layout.item_title_content;
                }
            }

            @Override
            public int getItemViewType(int position, NewsSummary bean) {
                if (!TextUtils.isEmpty(bean.getDigest())){
                    return TYPE_ITEM;
                }
                return TYPE_PHOTO_ITEM;
            }
        });
    }


    @Override
    public void convert(ViewHolderHelper helper, NewsSummary bean) {
        switch (helper.getLayoutId()){
            case R.layout.item_title_content://普通item
                setNomallValues(helper,bean,getPosition(helper));
                break;
            case R.layout.item_index_content_photo://带图文的item
                setPhotoValues(helper,bean,getPosition(helper));
                break;
        }
    }

    /**
     * 设置带图文的
     * @param helper
     * @param bean
     * @param position
     */
    private void setPhotoValues(ViewHolderHelper helper, NewsSummary bean, int position) {
        helper.setText(R.id.photo_title,bean.getTitle());
        helper.setText(R.id.photo_tv_time,bean.getPtime());
    }

    /**
     * 普通item设置
     * @param helper
     * @param bean 数据
     * @param postion 位置
     */
    private void setNomallValues(ViewHolderHelper helper,NewsSummary bean,int postion) {
        helper.setText(R.id.normal_tv_title,bean.getTitle());
    }
}
