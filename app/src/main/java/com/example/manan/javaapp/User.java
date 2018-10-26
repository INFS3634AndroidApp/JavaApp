package com.example.manan.javaapp;

import java.io.Serializable;

public class User implements Serializable {
    private int week;

    public User() {
    }

    public User(int week) {
        this.week = week;
    }


    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

}
