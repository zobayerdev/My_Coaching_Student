package com.trodev.mycoachingstudents.models;

public class UserStatus {

    String status, name, mobile, password, date, time, uid;

    public UserStatus() {
    }

    public UserStatus(String status, String name, String mobile, String password, String date, String time, String uid) {
        this.status = status;
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.date = date;
        this.time = time;
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
