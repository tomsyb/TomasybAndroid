package com.example.tomasyb.tomasybandroid.ui.addressbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tomasyb.baselib.yadapter.CommonAdapter;
import com.example.tomasyb.baselib.yadapter.ViewHolder;
import com.example.tomasyb.baselib.yadapter.decoration.DividerItemDecoration;
import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.agora.yview.indexbar.IndexBar;
import io.agora.yview.indexbar.suspension.SuspensionDecoration;
import io.agora.yview.swipdelmenu.SwipeMenuLayout;

/**
 * 通讯录带右滑删除
 */
public class AddressBookWithSwipDelActivity extends AppCompatActivity {

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
    private static final String INDEX_STRING_TOP = "↑";


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
        mAddressAdapter = new CommonAdapter<CityBean>(this, R.layout.item_address_book_swip, mDatas) {
            @Override
            public void convert(final ViewHolder holder, CityBean cityBean) {
                holder.setText(R.id.tv_name,cityBean.getCity());
                holder.setOnClickListener(R.id.btnDel, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((SwipeMenuLayout)holder.itemView).quickClose();
                        mDatas.remove(holder.getAdapterPosition());
                        mIndexbar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                                .setNeedRealIndex(true)//设置需要真实的索引
                                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                                .setmSourceDatas(mDatas)//设置数据
                                .invalidate();
                        notifyDataSetChanged();

                    }
                });
            }
        };
        mRv.setAdapter(mAddressAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
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
                //微信的头部 也是可以右侧IndexBar导航索引的，
                // 但是它不需要被ItemDecoration设一个标题titile
                mDatas.add((CityBean) new CityBean("新的朋友").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("群聊").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("标签").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("公众号").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));

                String[] data = getResources().getStringArray(R.array.provinces);
                for (int i = 0; i < data.length; i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(data[i]);//设置城市名称
                    mDatas.add(cityBean);
                }
                mAddressAdapter.setDatas(mDatas);
                mAddressAdapter.notifyDataSetChanged();

                mIndexbar.setmSourceDatas(mDatas)//设置数据
                        .invalidate();
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
        mAddressAdapter.notifyDataSetChanged();
    }
}
