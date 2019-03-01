package com.example.tomasyb.tomasybandroid.ui.interview;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.tomasyb.baselib.base.mvp.BaseActivity;
import com.example.tomasyb.baselib.base.mvp.IBasePresenter;
import com.example.tomasyb.baselib.widget.viewpager.ComPagerWithTitleAdapter;
import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.ui.interview.fragment.InterviewListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.agora.yview.tablayout.SlidingTabLayout;

/**
 *
 */
public class InterviewMainActivity extends BaseActivity {
    @BindView(R.id.slidtab)
    SlidingTabLayout mSlidTablayout;
    @BindView(R.id.interview_vp)
    ViewPager mVp;
    private final String[] mTitles = {"技能", "项目", "源码", "前端"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_interview_main;
    }

    @Override
    public void initView() {
        initTablayout();
    }

    private void initTablayout() {
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(InterviewListFragment.getInstance(i));
        }
        mSlidTablayout.setViewPager(mVp,mTitles,this,mFragments);
    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }
}
