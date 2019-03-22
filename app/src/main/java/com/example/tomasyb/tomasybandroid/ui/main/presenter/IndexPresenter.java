package com.example.tomasyb.tomasybandroid.ui.main.presenter;

import com.example.tomasyb.baselib.base.mvp.BasePresenter;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.util.FileUtils;
import com.example.tomasyb.baselib.util.GsonUtils;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.interview.entity.InterviewListEty;
import com.example.tomasyb.tomasybandroid.ui.main.contact.IndexContact;
import com.example.tomasyb.tomasybandroid.ui.main.entity.IndexMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * IndexPresenter
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-9-5.12:35
 * @since JDK 1.8
 */

public class IndexPresenter extends BasePresenter<IndexContact.view> implements IndexContact.presenter{

    public IndexPresenter(IndexContact.view view) {
        super(view);
    }


    @Override
    public ArrayList<Integer> getLocationBannerData() {
        ArrayList<Integer> localImages = new ArrayList<Integer>();
        // 本地图片集合
        for (int position = 1; position < 4; position++){
            localImages.add(FileUtils.getResId("ic_banner_test_" + position, R.drawable.class));
        }
        return localImages;
    }

    @Override
    public void getDialogData() {
        try {
            String json = GsonUtils.getJson(view.getContexts(), "indexmenu.json");
            List<IndexMenu.DataBean> mlist = new ArrayList<>();
            IndexMenu bean = GsonUtils.fromJson(json, IndexMenu.class);
            for (int i = 0; i < bean.getData().size(); i++) {
                mlist.add(bean.getData().get(i));
            }
            view.setDialogData(mlist);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
