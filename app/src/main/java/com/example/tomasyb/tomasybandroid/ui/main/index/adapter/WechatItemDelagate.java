package com.example.tomasyb.tomasybandroid.ui.main.index.adapter;

import com.example.tomasyb.baselib.rvadapter.base.ItemViewDelegate;
import com.example.tomasyb.baselib.rvadapter.base.ViewHolder;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.main.index.bean.IndexVpBean;

/**
 * 微信朋友圈的布局
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-1-23.15:12
 * @since JDK 1.8
 */

public class WechatItemDelagate implements ItemViewDelegate<IndexVpBean>{
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_weix;
    }

    @Override
    public boolean isForViewType(IndexVpBean item, int position) {
        return item.getType()==1;
    }

    @Override
    public void convert(ViewHolder holder, IndexVpBean indexVpBean, int position) {

    }
}
