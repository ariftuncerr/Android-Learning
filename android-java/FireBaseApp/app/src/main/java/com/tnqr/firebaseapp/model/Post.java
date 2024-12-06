package com.tnqr.firebaseapp.model;

public class Post {
    public String email;
    public String comment;
    public String downloadUrl;
    public String postName;

    public Post(String email, String comment, String downloadUrl, String postName) {
        this.email = email;
        this.comment = comment;
        this.downloadUrl = downloadUrl;
        this.postName = postName;
    }
}
