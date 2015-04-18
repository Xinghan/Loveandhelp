package com.xinghan.android.loveandhelp.ui.main;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.ui.SingleFragmentActivity;
import com.xinghan.android.loveandhelp.ui.news.NewsFragment;

/**
 * Created by xinghan on 3/29/15.
 */
public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }

}
