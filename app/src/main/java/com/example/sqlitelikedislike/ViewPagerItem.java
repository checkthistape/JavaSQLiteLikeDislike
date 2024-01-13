package com.example.sqlitelikedislike;

public class ViewPagerItem {
    int imageId;
    int likes, dislikes;

    public ViewPagerItem(int imageId, int likes, int dislikes){
        this.imageId = imageId;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
