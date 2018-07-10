package com.example.tomasyb.tomasybandroid.ui.study.entity;

/**
 * 测试实体类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-7-10.11:17
 * @since JDK 1.8
 */

public class TextEntity {
    private String name;
    private int age;

    public TextEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
