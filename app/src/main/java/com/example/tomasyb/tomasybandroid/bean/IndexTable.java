package com.example.tomasyb.tomasybandroid.bean;


import java.io.Serializable;

/**
 * 首页bean
 */
public class IndexTable implements Serializable {

    private String indexTopName;//首页顶部标

    public IndexTable() {
    }

    public IndexTable(String indexTopName) {
        this.indexTopName = indexTopName;
    }

    public String getIndexTopName() {
        return indexTopName;
    }

    public void setIndexTopName(String indexTopName) {
        this.indexTopName = indexTopName;
    }
}
