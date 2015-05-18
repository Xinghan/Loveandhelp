package com.xinghan.android.loveandhelp.ui.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.news.News;

import java.util.ArrayList;

/**
 * Created by xinghan on 5/17/15.
 */
public class NewsPagerFragment extends Fragment {
    public static final String EXTRA_NEWS_SLUG_ARRAY =
            "com.xinghan.android.loveandhelp.ui.news_count";
    public static final String EXTRA_NEWS_INDEX =
            "com.xinghan.android.loveandhelp.ui.news_index";

    private ViewPager mViewPager;
    private ArrayList<String> mNewsSlugList;
    private ArrayList<News> mNewsArrayList;
    public int mNewsCount;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ArrayList<String> mNewsSlugList = (ArrayList<String>)getActivity().getIntent().getStringArrayListExtra(EXTRA_NEWS_SLUG_ARRAY);
        String newsSlug = getActivity().getIntent().getStringExtra(EXTRA_NEWS_INDEX);
        mNewsCount = mNewsSlugList.size();

        mViewPager = new ViewPager(this.getActivity());
        mViewPager.setId(R.id.news_viewpager);

        FragmentManager fm = getChildFragmentManager();
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

        // Set initial index
        int newSlugListSize = mNewsSlugList.size();
        for(int i =0; i< newSlugListSize; ++i) {
            if(mNewsSlugList.get(i).equals(newsSlug)) {
                mViewPager.setCurrentItem(i);
            }
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) { }

            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) { }

            public void onPageSelected(int pos) {
                String slug = mNewsSlugList.get(pos);
                getActivity().setTitle(slug);
            }
        });
    }
}
