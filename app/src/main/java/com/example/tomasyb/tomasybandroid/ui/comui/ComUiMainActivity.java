package com.example.tomasyb.tomasybandroid.ui.comui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tomasyb.baselib.base.BaseActivity;
import com.example.tomasyb.baselib.widget.banner.BannerView;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.comui.entity.BannerEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComUiMainActivity extends BaseActivity {

    public static String[] titles = new String[]{
            "每周7件Tee不重样",
            "俏皮又知性 适合上班族的漂亮衬衫",
            "名侦探柯南",
            "境界之轮回",
            "我的英雄学院",
            "全职猎人",
    };
    public static String[] urls = new String[]{//750x500
            "https://s2.mogucdn.com/mlcdn/c45406/170422_678did070ec6le09de3g15c1l7l36_750x500.jpg",
            "https://s2.mogucdn.com/mlcdn/c45406/170420_1hcbb7h5b58ihilkdec43bd6c2ll6_750x500.jpg",
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg",
            "http://s18.mogucdn.com/p2/170204/upload_657jk682b5071bi611d9ka6c3j232_750x500.jpg",
            "http://s16.mogucdn.com/p2/170204/upload_56631h6616g4e2e45hc6hf6b7g08f_750x500.jpg",
            "http://s16.mogucdn.com/p2/170206/upload_1759d25k9a3djeb125a5bcg0c43eg_750x500.jpg"
    };

    public static class BannerViewFactory implements BannerView.ViewFactory<BannerEntity> {
        @Override
        public View create(BannerEntity item, int position, ViewGroup container) {
            ImageView iv = new ImageView(container.getContext());
            Glide.with(container.getContext().getApplicationContext()).load(item.getImg()).into(iv);
            return iv;
        }
    }

    @BindView(R.id.banner)
    BannerView mBanner;

    @Override
    public int getLayoutId() {
        return R.layout.activity_com_ui_main;
    }

    @Override
    public void initView() {
        List<BannerEntity> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerEntity item = new BannerEntity();
            item.setImg(urls[i]);
            item.setName(titles[i]);
            list.add(item);
        }

        mBanner.setViewFactory(new BannerViewFactory());
        mBanner.setDataList(list);
        mBanner.start();

    }

    @Override
    public void initPresenter() {

    }


    @OnClick(R.id.comui_btn_login)
    public void onViewClicked() {
    }

}
