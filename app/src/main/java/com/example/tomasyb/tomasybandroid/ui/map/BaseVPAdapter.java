package com.example.tomasyb.tomasybandroid.ui.map;

import android.content.Context;
import android.support.v4.util.LruCache;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by SongUp on 2018/1/21.
 */

public abstract class BaseVPAdapter<T> extends PagerAdapter {
    private List<T> dataList;
    //存放到Lrucache，用于清理不常用， 其中view.getTag key得到这是第几个View
    private LruCache<Integer, View> viewWeakHashMap;
    private LayoutInflater layoutInflater;
    private int converId;



    public BaseVPAdapter(Context context, int convertId, List<T> dataList) {
        this.dataList = dataList;
        this.converId = convertId;
        layoutInflater = LayoutInflater.from(context);
        viewWeakHashMap = new LruCache<>(20);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(container, position);
        container.addView(view);
        bindView(view, dataList.get(position));
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(getView(container, position));
    }

    //将布局添加到weakhashmap
    public View getView(ViewGroup container, int position){
        View view = viewWeakHashMap.get(position);
        if (view == null){
            view = layoutInflater.inflate(converId, container, false);
        }
        view.setTag(position);
        return view;
    }
    public abstract void bindView(View view, T data);



}
