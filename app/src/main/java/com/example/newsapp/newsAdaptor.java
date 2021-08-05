package com.example.newsapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

public class newsAdaptor extends ArrayAdapter<News> {

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "url");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public newsAdaptor(@NonNull Context context, List<News> newsList) {
        super(context, 0,newsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview=convertView;
        if(listview==null)
        {
            listview= LayoutInflater.from(getContext()).inflate(R.layout.custom_layout,parent,false);
        }
        News news=getItem(position);

        ImageView imageView=(ImageView) listview.findViewById(R.id.imageView);
        try{
            URL imageurl=new URL(news.getImgUrl());
            Glide.with(getContext()).load(imageurl).into(imageView);
        }
        catch(IOException e)
        {

        }

        TextView title=(TextView) listview.findViewById(R.id.Titleofnews);
        title.setText(news.getMtitle());

        TextView date=(TextView) listview.findViewById(R.id.Date);
        date.setText(news.getMpub_date());

        TextView des=(TextView) listview.findViewById(R.id.Descreption);
        des.setText(news.getMdescreption());

        TextView from=(TextView) listview.findViewById(R.id.newsfrom);
        from.setText(news.getMnewsfrom());

        TextView auth=(TextView) listview.findViewById(R.id.author);
        auth.setText(news.getAuthor());

        return listview;
    }
}
