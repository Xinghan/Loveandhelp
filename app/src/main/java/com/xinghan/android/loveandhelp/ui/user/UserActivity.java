package com.xinghan.android.loveandhelp.ui.user;

import android.support.v4.app.Fragment;

import com.xinghan.android.loveandhelp.ui.SingleFragmentActivity;
import com.xinghan.android.loveandhelp.ui.news.NewsFragment;

/**
 * Created by xinghan on 4/17/15.
 */
public class UserActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new UserFragment();
    }
}
