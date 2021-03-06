package com.xinghan.android.loveandhelp.core.news;

import android.util.Log;

import com.google.gson.Gson;
import com.xinghan.android.loveandhelp.network.ServerConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 4/15/15.
 */
public class NewsLoadThread extends Thread {

    private String mNewsSlug;

    public NewsLoadThread(String slug) {
        this.mNewsSlug = slug;
    }

    @Override
    public void run() {

        String newsURL = ServerConnection.SERVER + ServerConnection.RESTAPI + ServerConnection.NEWSAPI + mNewsSlug +"/.json";
        try {
            HttpURLConnection c = (HttpURLConnection) new URL(newsURL).openConnection();
            c.setConnectTimeout(3000);
            try {
                InputStream in = c.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                News news = new Gson().fromJson(reader, News.class);
                reader.close();
                EventBus.getDefault().post(new NewsLoadEvent(news));
            } catch (IOException e) {
                Log.e(getClass().getSimpleName(), "Exception parsing JSON news", e);
            }
            finally {
                c.disconnect();
            }
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Exception parsing JSON news", e);
        }

    }
}
