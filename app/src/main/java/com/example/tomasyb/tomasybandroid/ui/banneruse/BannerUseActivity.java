package com.example.tomasyb.tomasybandroid.ui.banneruse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.baselib.widget.banner.BGABanner;
import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  仅在 autoPlayAble 为 true 时才会生效「开发者使用该库时不用调用该方法，这里只是为了演示而已，界面可见时在 BGABanner 内部已经帮开发者调用了该方方法」
 *  mDefaultBanner.startAutoPlay();  mDefaultBanner.stopAutoPlay();
 *
 *  选择页码 mDefaultBanner.setCurrentItem(0);
 *
 *  当前页  mDefaultBanner.getCurrentItem()
 *
 *   广告总数  mDefaultBanner.getItemCount()
 *
 *   加载0页用封面图显示
 *   mDefaultBanner.setAdapter(this);
 *   mDefaultBanner.setAutoPlayAble(false);
 *   mDefaultBanner.setData(null, null);
 *   mDefaultBanner.showPlaceholder();
 *
 *   切换动画
 *   mDefaultBanner.setTransitionEffect(TransitionEffect.Cube);
 */
public class BannerUseActivity extends AppCompatActivity implements BGABanner.Adapter<ImageView,
        String>, BGABanner.Delegate<ImageView, String> {

    @BindView(R.id.banner_defaulte)
    BGABanner mBanner;
    @BindView(R.id.banner_main_flip)
    BGABanner mBanner2;

    private List<String> mBannerData;//banner图片集合
    private List<String> mTipData;// 下标文字集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_use);
        ButterKnife.bind(this);
        initView();
        setBannerData();
    }

    /**
     * 设置banner数据
     */
    private void setBannerData() {
        mBannerData = new ArrayList<>();
        mTipData = new ArrayList<>();
        mBannerData.add("http://bgashare.bingoogolapple.cn/banner/imgs/16.png");
        mBannerData.add("http://bgashare.bingoogolapple.cn/banner/imgs/17.png");
        mTipData.add("页码1");
        mTipData.add("页码2");
        /**
         * 设置是否开启自动轮播，需要在 setData 方法之前调用，并且调了该方法后必须再调用一次 setData 方法
         * 例如根据图片当图片数量大于 1 时开启自动轮播，等于 1 时不开启自动轮播
         */
        mBanner.setAutoPlayAble(mBannerData.size() > 1);//保证只有1页的时候不滚动
        mBanner.setAdapter(this);
        mBanner.setData(mBannerData, mTipData);

        mBanner2.setAutoPlayAble(mBannerData.size() > 1);//保证只有1页的时候不滚动
        mBanner2.setAdapter(this);
        mBanner2.setData(mBannerData, mTipData);
    }

    /**
     * 初始化数据
     */
    private void initView() {
        mBanner.setDelegate(this);
    }

    /**
     * 加载图片，
     * 这里实现接口的时候可以指定数据类型这里指定为String
     *
     * @param banner
     * @param itemView
     * @param model
     * @param position
     */
    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable String model, int
            position) {
        Glide.with(itemView.getContext())
                .load(model)
                .apply(new RequestOptions().placeholder(R.drawable.dialog_no_content_tip).error(R
                        .drawable.dialog_no_content_tip).dontAnimate().centerCrop())
                .into(itemView);
    }

    /**
     * 点击事件
     *
     * @param banner
     * @param itemView
     * @param model
     * @param position
     */
    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, @Nullable String model,
                                  int position) {
        ToastUtils.showLong("你点击了" + model);
    }



    @OnClick({R.id.btn_changeh, R.id.btn_canautoplay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_changeh:
                mBanner.setAspectRatio(2.0f);
                break;
            case R.id.btn_canautoplay:
                /**
                 * 设置是否开启自动轮播，需要在 setData 方法之前调用，并且调了该方法后必须再调用一次 setData 方法
                 * 例如根据图片当图片数量大于 1 时开启自动轮播，等于 1 时不开启自动轮播
                 */
                mBanner.setAutoPlayAble(true);
                break;
        }
    }
}
