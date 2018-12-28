package com.example.tomasyb.tomasybandroid.ui.addressbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tomasyb.baselib.yadapter.CommonAdapter;
import com.example.tomasyb.baselib.yadapter.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.tomasyb.baselib.yadapter.ViewHolder;
import com.example.tomasyb.baselib.yadapter.decoration.DividerItemDecoration;
import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.agora.yview.indexbar.IndexBar;
import io.agora.yview.indexbar.suspension.SuspensionDecoration;

/**
 * 通讯录带头布局的
 */
public class AddressBookWithHeaderActivity extends AppCompatActivity {

    @BindView(R.id.address_book_rv)
    RecyclerView mRv;
    @BindView(R.id.indexbar)
    IndexBar mIndexbar;
    @BindView(R.id.tvSideBarHint)
    TextView mTvSideBarHint;

    private CommonAdapter<CityBean> mAddressAdapter;
    private List<CityBean> mDatas;
    private SuspensionDecoration mDecoration;
    private LinearLayoutManager mManager;
    // 头布局
    private HeaderRecyclerAndFooterWrapperAdapter mHeadWrap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);
        ButterKnife.bind(this);
        initView();
        //模拟数据
        initData();
    }

    private void initView() {
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        mAddressAdapter = new CommonAdapter<CityBean>(this, R.layout.item_address_book, mDatas) {
            @Override
            public void convert(ViewHolder holder, CityBean cityBean) {
                holder.setText(R.id.tv_name,cityBean.getCity());
            }
        };
        // 头布局
        mHeadWrap = new HeaderRecyclerAndFooterWrapperAdapter(mAddressAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                holder.setText(R.id.tv_name,(String) o);
            }
        };
        mHeadWrap.setHeaderView(R.layout.item_address_book,"我是头部");


        mRv.setAdapter(mHeadWrap);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas).setHeaderViewCount(mHeadWrap.getHeaderViewCount()));
        // 如果add两个，那么按照先后顺序依次渲染
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //设置indexbar按下时中间弹出的显示文字
        mIndexbar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

    }

    private void initData() {
        //延迟2秒加载数据
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                String[] data = getResources().getStringArray(R.array.provinces);
                for (int i = 0; i < data.length; i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(data[i]);//设置城市名称
                    mDatas.add(cityBean);
                }
                mIndexbar.setmSourceDatas(mDatas)//设置数据
                        .setHeaderViewCount(mHeadWrap.getHeaderViewCount())//设置HeaderView数量
                        .invalidate();
                mAddressAdapter.setDatas(mDatas);
                mHeadWrap.notifyDataSetChanged();
                mDecoration.setmDatas(mDatas);
            }
        },500);
    }
    /**
     * 更新数据源
     *
     * @param view
     */
    public void updateDatas(View view) {
        for (int i = 0; i < 5; i++) {
            mDatas.add(new CityBean("东京"));
            mDatas.add(new CityBean("大阪"));
        }

        mIndexbar.setmSourceDatas(mDatas)
                .invalidate();
        mHeadWrap.notifyDataSetChanged();
    }
}
