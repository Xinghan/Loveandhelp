package com.xinghan.android.loveandhelp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;

/**
 * Created by xinghan on 3/24/15.
 */
public class NewsListFragment extends ListFragment {

    private ArrayList<News> mNewses;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.news_title);
        mNewses = NewsLab.getNewsLab(getActivity()).getNewses();
    }
}
