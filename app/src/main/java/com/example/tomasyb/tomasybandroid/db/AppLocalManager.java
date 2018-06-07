package com.example.tomasyb.tomasybandroid.db;

import com.example.tomasyb.tomasybandroid.R;
import com.example.tomasyb.tomasybandroid.base.IApplication;
import com.example.tomasyb.tomasybandroid.bean.IndexTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 本地数据管理
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-7.11:13
 * @since JDK 1.8
 */

public class AppLocalManager {
    /**
     *
     * @return 获取首页顶部栏目数据本地获取
     */
    public static List<IndexTable> getIndexTopStatic(){
        List<String> topName = Arrays.asList(IApplication.getAppContext().getResources().getStringArray(R.array.index_top_static));
        ArrayList<IndexTable> mTabList = new ArrayList<>();
        for (int i = 0; i < topName.size(); i++) {
            IndexTable indexTable = new IndexTable();
            indexTable.setIndexTopName(topName.get(i));
            mTabList.add(indexTable);
        }
        return mTabList;
    }
}
