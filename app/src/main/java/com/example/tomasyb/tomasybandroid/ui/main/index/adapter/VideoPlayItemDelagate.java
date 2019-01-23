package com.example.tomasyb.tomasybandroid.ui.main.index.adapter;

import com.example.tomasyb.baselib.rvadapter.base.ItemViewDelegate;
import com.example.tomasyb.baselib.rvadapter.base.ViewHolder;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.main.index.bean.IndexVpBean;

/**
 * 视频播放
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-1-23.17:15
 * @since JDK 1.8
 */

public class VideoPlayItemDelagate implements ItemViewDelegate<IndexVpBean>{
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_index_vp_dynamic_play;
    }

    @Override
    public boolean isForViewType(IndexVpBean item, int position) {
        return item.getType()==0;
    }

    @Override
    public void convert(ViewHolder holder, IndexVpBean indexVpBean, int position) {

    }
}
