package com.example.tomasyb.baselib.util;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomasyb.baselib.base.BaseApplication;

/**
 * TabLayout工具控制其属性
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-7.15:22
 * @since JDK 1.8
 */

public class TabLayoutUtils {

    public static void dynamicSetTabLayoutMode(TabLayout tabLayout) {
        int tabWidth = calculateTabWidth(tabLayout);
        int screenWidth = getScreenWith();

        if (tabWidth <= screenWidth) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }
    private static int calculateTabWidth(TabLayout tabLayout) {
        int tabWidth = 0;
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            final View view = tabLayout.getChildAt(i);
            view.measure(0, 0); // 通知父view测量，以便于能够保证获取到宽高
            tabWidth += view.getMeasuredWidth();
        }
        return tabWidth;
    }
    public static int getScreenWith() {
        return BaseApplication.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }
    public static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }
}
