package com.example.tomasyb.tomasybandroid.ui.main.fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tomasyb.baselib.adapter.BaseQuickAdapter;
import com.example.tomasyb.baselib.adapter.BaseViewHolder;
import com.example.tomasyb.baselib.base.BaseFragment;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.common.Constant;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;


/**
 * 首页fragment
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-4.15:50
 * @since JDK 1.8
 */

public class IndexFragment extends BaseFragment {
    @BindView(R.id.index_rv)
    RecyclerView mRv;

    List<String> mDatas;
    private BaseQuickAdapter<String,BaseViewHolder> mAdapter;
    @Override
    protected int getLayoutResource() {
        return R.layout.fg_main_index;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        mDatas = new ArrayList<>();
        mDatas.add("顶部Title的封装使用");
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_text,mDatas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.item_btn,item);
            }
        };
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        ARouter.getInstance().build(Constant.STUDY_COMUIMAIN).navigation();
                        break;
                }
            }
        });
        mRv.setAdapter(mAdapter);
    }


}
