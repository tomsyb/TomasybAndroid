package com.example.tomasyb.tomasybandroid.example.dagger.entity;

/**
 * Created by Tomasyb on 2018-4-24.
 */

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
