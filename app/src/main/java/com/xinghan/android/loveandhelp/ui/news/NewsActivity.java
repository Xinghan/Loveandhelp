package com.xinghan.android.loveandhelp.ui.news;

import android.support.v4.app.Fragment;

import com.xinghan.android.loveandhelp.ui.SingleFragmentActivity;


public class NewsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NewsFragment();
    }
}
