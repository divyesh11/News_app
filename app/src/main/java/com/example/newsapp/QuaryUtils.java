package com.example.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class QuaryUtils {

    private static final String ACCEPT_PROPERTY = "application/geo+json;version=1";
    private static final String USER_AGENT_PROPERTY = "newsapi.org (divyeshjivani000@gmail.com)";

    private QuaryUtils()
    {
    }

    public static List<News> extractnews(String requesturl)
    {
        List<News> news=new ArrayList<>();
        URL url=createurl(requesturl);

        String jsonresponse="";
        try {
            jsonresponse=makehttprequest(url);
            Log.d("makehttp","done");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        news=extractData(jsonresponse);
        Log.d("extract","done");
        if(news==null)
        {
            Log.d("news","null");
        }
        return news;
    }

    private static URL createurl(String url)
    {
        URL makeurl=null;
        try {
            makeurl=new URL(url);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        return makeurl;
    }

    private static String makehttprequest(URL url) throws IOException
    {
        Log.d("url", String.valueOf(url));
        String jsonresponse="";
        if(url==null)
        {
            return jsonresponse;
        }
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;

        try{
            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Accept",ACCEPT_PROPERTY);
            httpURLConnection.setRequestProperty("User-Agent",USER_AGENT_PROPERTY);

            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.connect();

            if(httpURLConnection.getResponseCode()==200) {
                inputStream = httpURLConnection.getInputStream();
                jsonresponse = readInputstream(inputStream);
            }

        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }finally {
            if(httpURLConnection!=null){
                 httpURLConnection.disconnect();
            }
            if(inputStream!=null)
            {
                inputStream.close();
            }
        }

        return jsonresponse;
    }

    private static String readInputstream(InputStream inputStream) throws IOException
    {
        StringBuilder builder=new StringBuilder();
        if(inputStream!=null) {

            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader bf=new BufferedReader(inputStreamReader);

            String Line=bf.readLine();

            while(Line!=null) {
                builder.append(Line);
                Line = bf.readLine();
            }

        }
        return builder.toString();
    }

    private static List<News> extractData(String jsonresponse)
    {
        if(TextUtils.isEmpty(jsonresponse))
        {
            return null;
        }
        List<News> ans=new ArrayList<News>();

        try {
            JSONObject baseresponse=new JSONObject(jsonresponse);
            JSONArray  resultarray=baseresponse.getJSONArray("articles");

            Log.d("size", String.valueOf(resultarray.length()));

            if(resultarray.length()>0)
            {
                for(int i=0;i<resultarray.length();i++)
                {
                     JSONObject currob=resultarray.getJSONObject(i);
                     JSONObject sources=currob.getJSONObject("source");

                     String title=currob.getString("title");
                    String des=currob.getString("description");
                    String date="Date : "+currob.getString("publishedAt").substring(0,10);
                    String from="From : "+sources.getString("name");
                    String url=currob.getString("url");
                    String auth="author : ";

                    if(currob.getString("author").equals("")||currob.getString("author").equals("null"))
                    {
                        auth+="Not avilable";
                    }
                    else
                    {
                        auth+=currob.getString("author");
                    }

                    String img=currob.getString("urlToImage");

                    ans.add(new News(title, url, date, des, from,img,auth));
                }
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return ans;
    }
}
