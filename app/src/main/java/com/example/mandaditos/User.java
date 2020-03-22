package com.example.mandaditos;

public class User {

    String number;
    String pass;
    String pass2;

    public User(){

    }

    public User(String number, String pass, String pass2) {
        this.number = number;
        this.pass = pass;
        this.pass2 = pass2;
    }

    public String getNumber() {
        return number;
    }

    public String getPass() {
        return pass;
    }

    public String getPass2() {
        return pass2;
    }
}
