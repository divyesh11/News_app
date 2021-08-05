package com.example.newsapp;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class News {
    private String mtitle;
    private String murl;
    private String mpub_date;
    private String mdescreption;
    private String mnewsfrom;
    private String imgUrl;
    private ImageView img;
    private String author;

    News(String title,String url,String date,String des,String from,String im,String auth)
    {
        mtitle=title;
        murl=url;
        mpub_date=date;
        mdescreption=des;
        mnewsfrom=from;
        imgUrl=im;
        author=auth;
    }

    public String getMdescreption() {
        return mdescreption;
    }

    public String getMtitle() {
        return mtitle;
    }

    public String getMnewsfrom() {
        return mnewsfrom;
    }

    public String getMpub_date() {
        return mpub_date;
    }

    public String getMurl() {
        return murl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getAuthor() {
        return author;
    }
}
