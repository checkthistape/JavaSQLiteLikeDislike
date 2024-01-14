package com.example.sqlitelikedislike;

import android.graphics.Bitmap;

public class ViewPagerItem {
    //int imageId;
    Bitmap image;
    int likes, dislikes;

    public ViewPagerItem(Bitmap image, int likes, int dislikes){
        this.image = image;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
