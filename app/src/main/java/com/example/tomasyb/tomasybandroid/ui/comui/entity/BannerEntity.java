package com.example.tomasyb.tomasybandroid.ui.comui.entity;

/**
 * Banner
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-14.16:38
 * @since JDK 1.8
 */

public class BannerEntity {
    private String img;
    private String name;

    @Override
    public String toString() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
