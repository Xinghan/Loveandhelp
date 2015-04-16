package com.xinghan.android.loveandhelp.core.news;

/**
 * Created by xinghan on 4/12/15.
 */
public class NewsListLoadEvent {
    public final NewsList mNewses;
    NewsListLoadEvent(NewsList newses) {
        this.mNewses = newses;
    }
}
