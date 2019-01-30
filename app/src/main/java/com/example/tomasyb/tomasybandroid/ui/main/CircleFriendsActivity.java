package com.example.tomasyb.tomasybandroid.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomasyb.baselib.rvadapter.CommonAdapter;
import com.example.tomasyb.baselib.rvadapter.base.ViewHolder;
import com.example.tomasyb.baselib.rvadapter.wrapper.HeaderAndFooterWrapper;
import com.example.tomasyb.baselib.util.ActivityUtils;
import com.example.tomasyb.baselib.util.ScreenUtils;
import com.example.tomasyb.baselib.util.SizeUtils;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.baselib.util.glide.GlideImageView;
import com.example.tomasyb.baselib.util.glide.widget.GridLayoutHelper;
import com.example.tomasyb.baselib.util.glide.widget.ImageData;
import com.example.tomasyb.baselib.util.glide.widget.NineImageView;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.common.Common;
import com.example.tomasyb.tomasybandroid.ui.main.entity.NineImgEty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.agora.yview.photoview.PicturePreviewActivity;

/**
 * 高仿朋友圈
 */
public class CircleFriendsActivity extends AppCompatActivity {

    @BindView(R.id.friends_rv)
    RecyclerView mFriendsRv;
    private CommonAdapter<NineImgEty> mNineImgAdapter;
    private List<NineImgEty> mDatas;
    private int minImgWidth;
    private int minImgHeight;
    private int maxImgHeight;
    private int maxImgWidth;
    private int cellWidth;
    private int cellHeight;
    private int margin;

    private HeaderAndFooterWrapper mHeadWrape;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_friends);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        margin = SizeUtils.dp2px(3);
        maxImgHeight = maxImgWidth= (ScreenUtils.getScreenWidth()-SizeUtils.dp2px(16)*2)*3/4;
        cellHeight = cellWidth = (maxImgWidth - margin * 3) / 3;
        minImgHeight = minImgWidth = cellWidth;
        mFriendsRv.setLayoutManager(new LinearLayoutManager(this));
        mNineImgAdapter = new CommonAdapter<NineImgEty>(this,R.layout.item_nine_images, Common.getImages()) {
            @Override
            protected void convert(ViewHolder holder, NineImgEty nineImgEty, int position) {
                holder.setText(R.id.tv_name,nineImgEty.desc);
                holder.setText(R.id.tv_content,"左边使用GlideImageView直接加载圆形图片，更多用法见本界面代码");
                /**
                 * GlideImageView全部使用方法
                 * enableState: 是否开启点击效果
                 * image21.fitCenter().load(gif2, R.mipmap.image_loading, 10);
                 * image22.fitCenter().load(gif1);
                 * image23.fitCenter().loadCircle(gif3);
                 * image24.fitCenter().loadDrawable(R.drawable.gif_robot_walk);加载本地GIF
                 */
                GlideImageView imgHead = (GlideImageView)holder.getView(R.id.weichat_imghead);
                //imgHead.loadCircle(nineImgEty.imgs.get(0).url);
                //imgHead.enableState(true).load(nineImgEty.imgs.get(0).url);
                //imgHead.load(nineImgEty.imgs.get(0).url, R.mipmap.image_loading);
                imgHead.load(nineImgEty.imgs.get(0).url, R.mipmap.image_loading, 5);
                /**
                 * 下面是9宫格图片使用方法
                 * loadGif:是否显示gif动态图
                 * enableRoundCorner是否圆角
                 * setRoundCornerRadius圆角度数
                 */
                NineImageView nineImg =(NineImageView) holder.getView(R.id.multiImageView);
                nineImg.loadGif(false)
                        .enableRoundCorner(true)
                        .setRoundCornerRadius(5)
                        .setData(nineImgEty.imgs,getLayoutHelper(nineImgEty.imgs));
                nineImg.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        ToastUtils.showLong("长按-->"+nineImgEty.desc);
                        return false;
                    }
                });
                nineImg.setOnItemClickListener(new NineImageView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("currentPosition",position);
                        ArrayList<String> mImgList=new ArrayList<>();
                        for (int i = 0; i < nineImgEty.imgs.size(); i++) {
                            mImgList.add(nineImgEty.imgs.get(i).url);
                        }
                        bundle.putStringArrayList("imgList",mImgList);
                        ActivityUtils.startActivity(bundle,PicturePreviewActivity.class);
                    }
                });
            }
        };
        mHeadWrape = new HeaderAndFooterWrapper(mNineImgAdapter);
        mHeadWrape.addHeaderView(getHeaderView());
        mFriendsRv.setAdapter(mHeadWrape);
    }

    private GridLayoutHelper getLayoutHelper(List<ImageData> list) {
        int spanCount = Common.getSize(list);
        if (spanCount == 1) {
            int width = list.get(0).realWidth;
            int height = list.get(0).realHeight;
            if (width > 0 && height > 0) {
                float whRatio = width * 1f / height;
                if (width > height) {
                    width = Math.max(minImgWidth, Math.min(width, maxImgWidth));
                    height = Math.max(minImgHeight, (int) (width / whRatio));
                } else {
                    height = Math.max(minImgHeight, Math.min(height, maxImgHeight));
                    width = Math.max(minImgWidth, (int) (height * whRatio));
                }
            } else {
                width = cellWidth;
                height = cellHeight;
            }
            return new GridLayoutHelper(spanCount, width, height, margin);
        }

        if (spanCount > 3) {
            spanCount = (int) Math.ceil(Math.sqrt(spanCount));
        }

        if (spanCount > 3) {
            spanCount = 3;
        }
        return new GridLayoutHelper(spanCount, cellWidth, cellHeight, margin);
    }

    /**
     * 获取头布局
     * @return
     */
    private View getHeaderView(){
        View view = getLayoutInflater().inflate(R.layout.item_wechat_headview,(ViewGroup)mFriendsRv.getParent(),false);
        return view;
    }
}
