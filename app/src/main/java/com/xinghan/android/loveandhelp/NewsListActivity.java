package com.xinghan.android.loveandhelp;

import android.support.v4.app.Fragment;

/**
 * Created by xinghan on 3/24/15.
 */
public class NewsListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NewsListFragment();
    }
}
