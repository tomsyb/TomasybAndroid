package com.example.tomasyb.tomasybandroid.ui.view;

import android.support.annotation.NonNull;

/**
 * 饼状图的数据封装
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-12-18.16:37
 * @since JDK 1.8
 */

public class PieChart {
    // 用户关心
    private String name;//名
    private float value;//值
    private float percentage;// 百分比

    // 非用户关心数据
    private int color = 0;// 颜色
    private float angle = 0;// 角度

    public PieChart(@NonNull String name, @NonNull float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
