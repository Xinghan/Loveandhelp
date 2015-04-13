package com.xinghan.android.loveandhelp.core.news;

import java.util.List;

/**
 * Created by xinghan on 4/12/15.
 */
public class NewsList {
    private List<News> mNewsList;

    public NewsList(List<News> newsList) {
        this.mNewsList = newsList;
    }
    public List<News> getNewsList() { return mNewsList; }

}
