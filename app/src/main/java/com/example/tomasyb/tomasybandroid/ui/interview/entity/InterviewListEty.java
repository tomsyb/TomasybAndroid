package com.example.tomasyb.tomasybandroid.ui.interview.entity;

import java.util.List;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-3-1.10:08
 * @since JDK 1.8
 */

public class InterviewListEty {

    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * type : 0
         * name : create
         * htmlurl : create
         * content : 可用于获取一个被观察的对象
         */

        private int type;
        private String name;
        private String htmlurl;
        private String content;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHtmlurl() {
            return htmlurl;
        }

        public void setHtmlurl(String htmlurl) {
            this.htmlurl = htmlurl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
