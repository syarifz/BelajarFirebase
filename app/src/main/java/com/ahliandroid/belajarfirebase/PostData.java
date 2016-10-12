package com.ahliandroid.belajarfirebase;

/**
 * Created by SyarifZ on 10/13/2016.
 */

public class PostData {

    public String post;
    public String fullname;
    public long timestamp;

    public PostData() {
    }

    public PostData(String post, String fullname, long timestamp) {
        this.post = post;
        this.fullname = fullname;
        this.timestamp = timestamp;
    }
}
