package com.example.tomasyb.tomasybandroid.ui.study.uiexample;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.base.mvp.BasePresenter;
import com.example.tomasyb.baselib.util.LogUtils;
import com.example.tomasyb.baselib.widget.banner.BannerView;
import com.example.tomasyb.baselib.widget.titlebar.CommonTitleBar;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.comui.entity.BannerEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Ui相关
 */
@Route(path = "/study/ComUiMainActivity")
public class ComUiMainActivity extends BaseActivity {
    @BindView(R.id.banner)
    BannerView mBanner;
    @BindView(R.id.banner1)
    BannerView mBanner1;
    @BindView(R.id.comui_title)
    CommonTitleBar mTitleBar;
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
        public View create(BannerEntity item, final int position, ViewGroup container) {
            ImageView iv = new ImageView(container.getContext());
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e("你点击的是-->"+position);
                }
            });
            //Glide.with(container.getContext().getApplicationContext()).load(item.getImg()).into(iv);
            return iv;
        }
    }

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

        mBanner1.setViewFactory(new BannerViewFactory());
        mBanner1.setDataList(list);
        mBanner1.start();

        mTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_TEXT){
                    onBackPressed();
                }
            }
        });
        mTitleBar.setBackgroundColor(getResources().getColor(R.color.main));
        mTitleBar.setStatusBarColor(getResources().getColor(R.color.b_main_red));

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


}
