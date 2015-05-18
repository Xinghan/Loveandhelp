package com.xinghan.android.loveandhelp.ui.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xinghan.android.loveandhelp.ui.SingleFragmentActivity;

/**
 * Created by xinghan on 5/17/15.
 */
public class NewsPagerActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NewsPagerFragment();
    }
}
