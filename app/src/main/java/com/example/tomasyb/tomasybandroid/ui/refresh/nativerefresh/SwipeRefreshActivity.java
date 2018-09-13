package com.example.tomasyb.tomasybandroid.ui.refresh.nativerefresh;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.tomasyb.baselib.adapter.BaseQuickAdapter;
import com.example.tomasyb.baselib.adapter.BaseViewHolder;
import com.example.tomasyb.baselib.util.ToastUtils;
import com.example.tomasyb.tomasybandroid.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
interface RequestCallBack {
    void success(List<Status> data);

    void fail(Exception e);
}

class Request extends Thread {
    private static final int PAGE_SIZE = 15;
    private int mPage;
    private RequestCallBack mCallBack;
    private Handler mHandler;

    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    public Request(int page, RequestCallBack callBack) {
        mPage = page;
        mCallBack = callBack;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        try {Thread.sleep(500);} catch (InterruptedException e) {}

        if (mPage == 2 && mFirstError) {
            mFirstError = false;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallBack.fail(new RuntimeException("fail"));
                }
            });
        } else {
            int size = PAGE_SIZE;
            if (mPage == 1) {
                if (mFirstPageNoMore) {
                    size = 1;
                }
                mFirstPageNoMore = !mFirstPageNoMore;
                if (!mFirstError) {
                    mFirstError = true;
                }
            } else if (mPage == 4) {
                size = 1;
            }

            final int dataSize = size;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    List<Status> list = new ArrayList<>();
                    for (int i = 0; i < dataSize; i++) {
                        Status status = new Status();
                        status.setUserName("Chad" + i);
                        status.setCreatedAt("04/05/" + i);
                        status.setRetweet(i % 2 == 0);
                        status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
                        status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
                        list.add(status);
                    }
                    mCallBack.success(list);
                }
            });
        }
    }
}

/**
 * SwipeRefresh刷新界面
 */
public class SwipeRefreshActivity extends AppCompatActivity {
    private int PAGE_SIZE = 10;
    private int mPage = 1;
    private RecyclerView mRv;
    private SwipeRefreshLayout mSwip;
    private BaseQuickAdapter<Status,BaseViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_refresh);
        initView();
        initData();
    }

    private void initData() {
        refresh();
    }


    private void initView() {
        mRv = findViewById(R.id.refresh_rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));

        mSwip = findViewById(R.id.swipsh);
        mSwip.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwip.setRefreshing(true);
        mSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        mAdapter = new BaseQuickAdapter<Status, BaseViewHolder>(R.layout.item_text,null) {
            @Override
            protected void convert(BaseViewHolder helper, Status item) {
                helper.setText(R.id.item_btn,item.getText());
            }
        };
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMre();
            }
        },mRv);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRv.setAdapter(mAdapter);
    }

    /***
     * 刷新
     */
    private void refresh() {
        mPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        new Request(mPage, new RequestCallBack() {
            @Override
            public void success(List<Status> data) {
                setData(true, data);
                mAdapter.setEnableLoadMore(true);
                mSwip.setRefreshing(false);
            }

            @Override
            public void fail(Exception e) {
                ToastUtils.showLong("网络错误");
                mAdapter.setEnableLoadMore(true);
                mSwip.setRefreshing(false);
            }
        }).start();

    }

    // 加载跟多
    private void loadMre() {
        new Request(mPage, new RequestCallBack() {
            @Override
            public void success(List<Status> data) {
                setData(false, data);
            }

            @Override
            public void fail(Exception e) {
                mAdapter.loadMoreFail();
                ToastUtils.showLong("网络错误");
            }
        }).start();
    }

    /**
     * 设置数据
     * @param isRefresh
     * @param data
     */
    private void setData(boolean isRefresh, List data) {
        mPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            ToastUtils.showLong("没有跟多数据");
        } else {
            mAdapter.loadMoreComplete();
        }
    }
}
