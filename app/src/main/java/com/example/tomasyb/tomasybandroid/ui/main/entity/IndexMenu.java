package com.example.tomasyb.tomasybandroid.ui.main.entity;

import java.util.List;

/**
 * 首页菜单数据
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-3-22.11:08
 * @since JDK 1.8
 */

public class IndexMenu {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : create
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
