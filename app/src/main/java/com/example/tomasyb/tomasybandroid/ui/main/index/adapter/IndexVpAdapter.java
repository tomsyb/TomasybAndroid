package com.example.tomasyb.tomasybandroid.ui.main.index.adapter;

import android.content.Context;

import com.example.tomasyb.baselib.rvadapter.MultiItemTypeAdapter;
import com.example.tomasyb.tomasybandroid.ui.main.index.bean.IndexVpBean;

import java.util.List;

/**
 * 首页viewpageer的适配器
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-1-23.15:07
 * @since JDK 1.8
 */

public class IndexVpAdapter extends MultiItemTypeAdapter<IndexVpBean>{

    public IndexVpAdapter(Context context, List<IndexVpBean> datas) {
        super(context, datas);
        addItemViewDelegate(new VideoPlayItemDelagate());
        addItemViewDelegate(new WechatItemDelagate());
    }
}
