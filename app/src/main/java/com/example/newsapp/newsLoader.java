package com.example.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

public class newsLoader extends AsyncTaskLoader<List<News>> {
    private static final String LOG_TAG = newsLoader.class.getName();

    /** Query URL */
    private String mUrl;
    public newsLoader(Context context, String url) {
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if(mUrl==null)
        {
            return null;
        }
        List<News> ans=QuaryUtils.extractnews(mUrl);
//        ans.addAll(QuaryUtils.extractnews(mUrl+"&page=1"));
//        ans.addAll(QuaryUtils.extractnews(mUrl+"&page=2"));
//        ans.addAll(QuaryUtils.extractnews(mUrl+"&page=3"));
        Log.d("loader","done");
        return ans;
    }
}
