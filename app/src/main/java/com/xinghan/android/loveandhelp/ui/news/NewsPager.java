package com.xinghan.android.loveandhelp.ui.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.news.News;

import java.util.ArrayList;

/**
 * Created by xinghan on 5/18/15.
 */
public class NewsPager extends ActionBarActivity {
    public static final String EXTRA_NEWS_SLUG_ARRAY =
            "com.xinghan.android.loveandhelp.ui.news_count";
    public static final String EXTRA_NEWS_INDEX =
            "com.xinghan.android.loveandhelp.ui.news_index";

    private ViewPager mViewPager;

    private ArrayList<String> mNewsSlugList;
    private ArrayList<News> mNewsArrayList;
    public int mNewsCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.news_viewpager);
        setContentView(mViewPager);

        final ArrayList<String> mNewsSlugList = (ArrayList<String>)getIntent().getStringArrayListExtra(EXTRA_NEWS_SLUG_ARRAY);
        String newsSlug = getIntent().getStringExtra(EXTRA_NEWS_INDEX);
        mNewsCount = mNewsSlugList.size();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return mNewsCount;
            }

            @Override
            public Fragment getItem(int pos) {
                String newsSlug = mNewsSlugList.get(pos);
                return NewsFragment.newInstance(newsSlug);
            }
        });

        int newSlugListSize = mNewsSlugList.size();
        for(int i =0; i< newSlugListSize; ++i) {
            if(mNewsSlugList.get(i).equals(newsSlug)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
