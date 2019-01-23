package com.example.tomasyb.tomasybandroid.ui.main.index.bean;

/**
 * 功能
 *
 * @author 严博
 * @version 1.0.0
 * @date 2019-1-23.15:09
 * @since JDK 1.8
 */

public class IndexVpBean {
    private String time;
    private String content;
    private String title;
    /**
     * 布局类型
     */
    private int type;

    public IndexVpBean(String time, String content, String title, int type) {
        this.time = time;
        this.content = content;
        this.title = title;
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
