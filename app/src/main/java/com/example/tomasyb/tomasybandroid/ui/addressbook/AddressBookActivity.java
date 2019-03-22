package com.example.tomasyb.tomasybandroid.ui.addressbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tomasyb.baselib.rvadapter.CommonAdapter;
import com.example.tomasyb.baselib.widget.LoadingDialog;
import com.example.tomasyb.baselib.rvadapter.DividerItemDecoration;
import com.example.tomasyb.tomasybandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.agora.yview.dialog.BaseDialog;
import io.agora.yview.indexbar.IndexBar;
import io.agora.yview.indexbar.suspension.SuspensionDecoration;

/**
 * 通讯录
 */
public class AddressBookActivity extends AppCompatActivity {

    @BindView(R.id.address_book_rv)
    RecyclerView mRv;
    @BindView(R.id.indexbar)
    IndexBar mIndexbar;
    @BindView(R.id.tvSideBarHint)
    TextView mTvSideBarHint;
    @BindView(R.id.tv_actionbar_title)
    TextView mTitle;
    @BindView(R.id.iv_actionbar_left)
    ImageView iv_actionbar_left;

    private CommonAdapter<CityBean> mAddressAdapter;

    private List<CityBean> mDatas;
    private SuspensionDecoration mDecoration;
    private LinearLayoutManager mManager;
    private static final String INDEX_STRING_TOP = "↑";
    /**
     * 基础弹框
     */
    private BaseDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);
        ButterKnife.bind(this);
        initView();
        // 模拟数据
        initData();
    }

    private void initView() {
        mDatas = new ArrayList<>();
        mTitle.setText("通讯录");
        iv_actionbar_left.setVisibility(View.VISIBLE);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        mAddressAdapter = new CommonAdapter<CityBean>(this, R.layout.item_address_book, mDatas) {
            @Override
            protected void convert(com.example.tomasyb.baselib.rvadapter.base.ViewHolder holder,
                                   CityBean cityBean, int position) {
                holder.setText(R.id.tv_name, cityBean.getCity());
                holder.setOnClickListener(R.id.content, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog();
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
        LoadingDialog.showDialogForLoading(this);
        //延迟2秒加载数据
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                //微信的头部 也是可以右侧IndexBar导航索引的，
                // 但是它不需要被ItemDecoration设一个标题titile
                mDatas.add((CityBean) new CityBean("新的朋友").setTop(true).setBaseIndexTag
                        (INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("群聊").setTop(true).setBaseIndexTag
                        (INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("标签").setTop(true).setBaseIndexTag
                        (INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("公众号").setTop(true).setBaseIndexTag
                        (INDEX_STRING_TOP));

                String[] data = getResources().getStringArray(R.array.provinces);
                for (int i = 0; i < data.length; i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(data[i]);//设置城市名称
                    mDatas.add(cityBean);
                }
                mAddressAdapter.notifyDataSetChanged();
                mIndexbar.setmSourceDatas(mDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mDatas);
                LoadingDialog.cancelDialogForLoading();
            }
        }, 500);
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

    @OnClick({R.id.iv_actionbar_left, R.id.tv_actionbar_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_actionbar_left:
                finish();
                break;
            case R.id.tv_actionbar_left:
                break;
            default:
                break;
        }
    }

    /**
     * 显示弹框
     */
    private void showDialog() {
        dialog = new BaseDialog(this);
        dialog.contentView(R.layout.dialog_base_center)
                .gravity(0)
                .canceledOnTouchOutside(true).show();
        dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
