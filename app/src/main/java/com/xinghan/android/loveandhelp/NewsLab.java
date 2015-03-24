package com.xinghan.android.loveandhelp;

import android.content.Context;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by xinghan on 3/23/15.
 */
public class NewsLab {
    private static NewsLab sNewsLab;
    private Context mAppContext;
    private ArrayList<News> mNewses;

    private NewsLab(Context appContext) {
        mAppContext = appContext;
        mNewses = new ArrayList<News>();
    }

    public NewsLab getNewsLab(Context c) {
        if(sNewsLab == null) {
            sNewsLab = new NewsLab(c.getApplicationContext());
        }

        return sNewsLab;
    }

    public ArrayList<News> getNewses() {
        return mNewses;
    }

    public News getNews(UUID id) {
        for(News n : mNewses) {
            if(id == n.getId()) {
                return n;
            }
        }

        return null;
    }
}
