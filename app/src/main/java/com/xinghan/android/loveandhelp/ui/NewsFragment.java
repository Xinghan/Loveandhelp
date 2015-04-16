package com.xinghan.android.loveandhelp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.news.NewsListLoadEvent;
import com.xinghan.android.loveandhelp.core.news.NewsLoadEvent;
import com.xinghan.android.loveandhelp.core.news.NewsLoadThread;

import java.util.UUID;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 3/24/15.
 */
public class NewsFragment extends Fragment{
    public static final String EXTRA_NEWS_ID =
        "com.xinghan.android.loveandhelp.ui.news_id";

    private TextView mTitleView;
    private TextView mContentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        setHasOptionsMenu(true);
        String newsSlug = (String)getActivity().getIntent().getSerializableExtra(EXTRA_NEWS_ID);
        new NewsLoadThread(newsSlug).start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
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
        titleView.setText(event.mNews.getTitle());
        contentView.setText(event.mNews.getBody());
    }
}
