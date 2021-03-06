package com.xinghan.android.loveandhelp.ui.news;

import android.annotation.TargetApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.news.News;
import com.xinghan.android.loveandhelp.core.news.NewsListLoadEvent;
import com.xinghan.android.loveandhelp.core.news.NewsListLoadThread;
import com.xinghan.android.loveandhelp.core.news.NewsLoadEvent;
import com.xinghan.android.loveandhelp.core.news.NewsLoadThread;

import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 3/24/15.
 */
public class NewsFragment extends Fragment{
    public static final String EXTRA_NEWS_SLUG =
        "com.xinghan.android.loveandhelp.ui.news_id";
    public static final String EXTRA_NEWS_SLUG_ARRAY =
            "com.xinghan.android.loveandhelp.ui.news_count";

    private TextView mTitleView;
    private TextView mContentView;
    private ViewPager mViewPager;
    private ArrayList<String> mNewsSlugList;
    private ArrayList<News> mNewsArrayList;
    public int mNewsCount;
    private NewsFragment mNewsFragment;
    private String mNewsSlug;

    public static NewsFragment newInstance(String newsSlug) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_NEWS_SLUG, newsSlug);

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public void setSlug(String slug) {
        this.mNewsSlug = slug;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        mNewsSlug = (String)getArguments().getSerializable(EXTRA_NEWS_SLUG);
        new NewsLoadThread(mNewsSlug).start();
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
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.news_detail_body);
        view.findViewById(R.id.news_detail_body);
        contentView.setMovementMethod(new ScrollingMovementMethod());
        titleView.setText(event.mNews.getTitle());
        contentView.setText(event.mNews.getBody());
    }
}
