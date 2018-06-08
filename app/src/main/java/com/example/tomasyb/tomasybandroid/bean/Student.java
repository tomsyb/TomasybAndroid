package com.example.tomasyb.tomasybandroid.bean;

import java.util.List;

/**
 * 学生类
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-6-8.15:49
 * @since JDK 1.8
 */

public class Student {
    private String name;
    private List<Course> mCourse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getmCourse() {
        return mCourse;
    }

    public void setmCourse(List<Course> mCourse) {
        this.mCourse = mCourse;
    }

    public static class Course {
        private String course;

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }
    }
}
