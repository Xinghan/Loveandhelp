package com.xinghan.android.loveandhelp.ui.news;

import android.support.v4.app.Fragment;

import com.xinghan.android.loveandhelp.ui.SingleFragmentActivity;


public class NewsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        String slug = (String)getIntent().getSerializableExtra(NewsFragment.EXTRA_NEWS_SLUG);
        return NewsFragment.newInstance(slug);
    }
}
