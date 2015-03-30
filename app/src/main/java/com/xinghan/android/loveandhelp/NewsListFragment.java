package com.xinghan.android.loveandhelp;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.annotation.TargetApi;

import java.util.ArrayList;
import java.util.zip.Inflater;

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
        Log.d(LogNewsList, "Creating menu in onCreate.");
        getActivity().setTitle(R.string.news_title);
        mNewses = NewsLab.getNewsLab(getActivity()).getNewses();

        ArrayAdapter<News> adapter =
           new ArrayAdapter<News>(getActivity(),
                   android.R.layout.simple_list_item_1,
                   mNewses);

        setListAdapter(adapter);
        setHasOptionsMenu(true);
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
}
