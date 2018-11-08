package com.example.tomasyb.tomasybandroid.ui.map.pre;

import com.example.tomasyb.baselib.base.mvp.BasePresenter;
import com.example.tomasyb.tomasybandroid.ui.map.cardvp.CardItem;
import com.example.tomasyb.tomasybandroid.ui.map.contact.MapContact;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-11-7.19:18
 * @since JDK 1.8
 */

public class MapPresenter extends BasePresenter<MapContact.view> implements MapContact.presenter {
    public MapPresenter(MapContact.view view) {
        super(view);
    }

    @Override
    public void addVpData() {
        List<CardItem> mlist = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            CardItem cardItem = new CardItem("名字"+i,"年龄"+i);
            mlist.add(cardItem);
        }
        view.setVpData(mlist);
    }
}
