package com.xinghan.android.loveandhelp.ui;

import android.support.v4.app.Fragment;

import com.xinghan.android.loveandhelp.ui.SingleFragmentActivity;

/**
 * Created by xinghan on 3/24/15.
 */
public class NewsListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NewsListFragment();
    }
}
