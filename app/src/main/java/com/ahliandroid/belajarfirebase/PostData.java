package com.ahliandroid.belajarfirebase;

/**
 * Created by SyarifZ on 10/13/2016.
 */

public class PostData {

    public String post;
    public String fullName;
    public long timestamp;

    public PostData() {
    }

    public PostData(String post, String fullName, long timestamp) {
        this.post = post;
        this.fullName = fullName;
        this.timestamp = timestamp;
    }
}
