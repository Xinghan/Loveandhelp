package com.xinghan.android.loveandhelp.ui.news;

import android.annotation.TargetApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.news.News;
import com.xinghan.android.loveandhelp.core.news.NewsListLoadEvent;
import com.xinghan.android.loveandhelp.core.news.NewsListLoadThread;
import com.xinghan.android.loveandhelp.core.news.NewsLoadEvent;
import com.xinghan.android.loveandhelp.core.news.NewsLoadThread;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.UUID;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 3/24/15.
 */
public class NewsFragment extends Fragment{
    public static final String EXTRA_NEWS_ID =
        "com.xinghan.android.loveandhelp.ui.news_id";
    public static final String EXTRA_NEWS_COUNT =
            "com.xinghan.android.loveandhelp.ui.news_count";

    private TextView mTitleView;
    private TextView mContentView;
    private ViewPager mViewPager;
    private ArrayList<News> mNewsArrayList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        setHasOptionsMenu(true);
        String newsSlug = (String)getActivity().getIntent().getSerializableExtra(EXTRA_NEWS_ID);
        Integer newsCount = (Integer)getActivity().getIntent().getSerializableExtra(EXTRA_NEWS_COUNT);

        mViewPager = new ViewPager(this.getActivity());
        mViewPager.setId(R.id.news_viewpager);

        FragmentManager fm = getChildFragmentManager();
        mViewPager.setAdapter(new NewsViewPagerAdapter(fm));

        new NewsListLoadThread().start();
        new NewsLoadThread(newsSlug, newsCount).start();
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.news_details_menu, menu);
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }
    public void onEventMainThread(NewsLoadEvent event) {
        View view = getView();
        TextView titleView = (TextView)view.findViewById(R.id.news_details_title);
        TextView contentView = (TextView)view.findViewById(R.id.news_details_content);
        contentView.setMovementMethod(new ScrollingMovementMethod());
        titleView.setText(event.mNews.getTitle());
        contentView.setText(event.mNews.getBody());
    }

    public static class NewsViewPagerAdapter extends FragmentPagerAdapter {
        public NewsViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int num) {
            if (num == 0) {
                return new ItemsListFragment();
            } else {
                return new FavsListFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }
}
