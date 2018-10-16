package com.example.manan.javaapp;

public class User {
    private String uid;
    private int week;

    public User(String uid, int week) {
        this.uid = uid;
        this.week = week;
    }

    public String getUid() {
        return uid;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
