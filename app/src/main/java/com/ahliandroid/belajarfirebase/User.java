package com.ahliandroid.belajarfirebase;

/**
 * Created by SyarifZ on 10/12/2016.
 */

public class User {
    public String email;
    public String fullname;

    public User() {
    }

    public User(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }
}
