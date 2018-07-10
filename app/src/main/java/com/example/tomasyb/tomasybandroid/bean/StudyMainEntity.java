package com.example.tomasyb.tomasybandroid.bean;

import java.util.List;

/**
 * Created by yanbo on 2018/7/7.
 * 学习主页面的实体类
 */

public class StudyMainEntity {

    private List<RxjavaBean> rxjava;
    private List<RetrofitBean> retrofit;

    public List<RxjavaBean> getRxjava() {
        return rxjava;
    }

    public void setRxjava(List<RxjavaBean> rxjava) {
        this.rxjava = rxjava;
    }

    public List<RetrofitBean> getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(List<RetrofitBean> retrofit) {
        this.retrofit = retrofit;
    }

    public static class RxjavaBean {
        /**
         * name : 标题
         * content : 描述
         */

        private String name;
        private String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class RetrofitBean {
        /**
         * name : 标题
         * content : 描述
         */

        private String name;
        private String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
