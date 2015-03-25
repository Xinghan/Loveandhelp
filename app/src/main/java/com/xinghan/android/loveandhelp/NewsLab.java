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

        // TODO: Add tests data for now, will add unit test later
        for(int i=0; i<100; ++i) {
            News news = new News();
            news.setTitle("News" + i);
            news.setContent("News Content" + i);
            news.setDescription("News description" + i);
            mNewses.add(news);
        }
    }

    public static NewsLab getNewsLab(Context c) {
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
