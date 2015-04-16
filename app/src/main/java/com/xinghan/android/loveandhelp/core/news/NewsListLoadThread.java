package com.xinghan.android.loveandhelp.core.news;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 4/12/15.
 */
public class NewsListLoadThread extends Thread{
    static final String NEWS_URL = "http://192.168.1.7:8000/api/entries/.json";

    @Override
    public void run() {
        try {
            HttpURLConnection c = (HttpURLConnection) new URL(NEWS_URL).openConnection();

            try {
                InputStream in = c.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                News[] newses = new Gson().fromJson(reader, News[].class);
                NewsList questions = new NewsList(Arrays.asList(newses));
                reader.close();
                EventBus.getDefault().post(new NewsListLoadEvent(questions));
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
