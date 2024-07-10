package com.trodev.mycoachingstudents.models;

public class User {

    public String uname, email, pass, num, image;

    public User() {

    }

    public User(String uname, String email, String pass, String num, String image) {
        this.uname = uname;
        this.email = email;
        this.pass = pass;
        this.num = num;
        this.image = image;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
