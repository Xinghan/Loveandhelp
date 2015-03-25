package com.xinghan.android.loveandhelp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


public class NewsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NewsFragment();
    }
}
