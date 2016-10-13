package com.ahliandroid.belajarfirebase;

/**
 * Created by SyarifZ on 10/12/2016.
 */

public class User {
    public String email;
    public String fullName;

    public User() {
    }

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
