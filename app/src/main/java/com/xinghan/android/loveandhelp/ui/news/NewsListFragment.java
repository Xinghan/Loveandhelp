package com.xinghan.android.loveandhelp.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xinghan.android.loveandhelp.core.news.News;
import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.news.NewsListLoadEvent;
import com.xinghan.android.loveandhelp.core.news.NewsListLoadThread;

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
        new NewsListLoadThread().start();
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
        News news = ((NewsAdapter)getListAdapter()).getItem(position);
        // Start single news activity
        Intent newsIntent = new Intent(getActivity(), NewsActivity.class);
        newsIntent.putExtra(NewsFragment.EXTRA_NEWS_ID, news.getSlug());
        startActivity(newsIntent);
    }

    public void onEventMainThread(NewsListLoadEvent event) {
        setListAdapter(new NewsAdapter(event.mNewses.getNewsList()));
    }

    class NewsAdapter extends ArrayAdapter<News> {
        NewsAdapter(List<News> newses) {
            super(getActivity(), R.layout.fragment_newslist_row, R.id.title, newses);
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
