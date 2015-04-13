package com.xinghan.android.loveandhelp.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.annotation.TargetApi;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xinghan.android.loveandhelp.core.news.News;
import com.xinghan.android.loveandhelp.NewsLab;
import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.news.NewsLoadEvent;
import com.xinghan.android.loveandhelp.core.news.NewsLoadThread;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 3/24/15.
 */
public class NewsListFragment extends ListFragment {

    public static final String LogNewsList = "NewsListFragment";

    private ArrayList<News> mNewses;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.news_title);

        new NewsLoadThread().start();
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }

    public void onEventMainThread(NewsLoadEvent event) {
        setListAdapter(new NewsAdapter(event.mNewses.getNewsList()));
    }


    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            Log.d(LogNewsList, "Version is 11");
            if (mSubtitleVisible) {
                getActivity().getActionBar().setSubtitle(R.string.subtitle);
            }
        }
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_news, menu);
    }

    class NewsAdapter extends ArrayAdapter<News> {
        NewsAdapter(List<News> newses) {
            super(getActivity(), R.layout.newslist_row, R.id.title, newses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);
            News news = getItem(position);
            ImageView icon=(ImageView)row.findViewById(R.id.news_image);
            if(icon == null) {
                icon = new ImageView(getContext());
            }

            if(!news.getImage().isEmpty()) {
                Picasso.with(getActivity()).load(news.getImage())
                        .fit().centerCrop().placeholder(R.drawable.placeholder)
                        .into(icon);
            }

            TextView title = (TextView)row.findViewById(R.id.title);
            title.setText(Html.fromHtml(getItem(position).getTitle()));
            return row;
        }
    }
}
