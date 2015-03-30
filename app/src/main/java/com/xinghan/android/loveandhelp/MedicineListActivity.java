package com.xinghan.android.loveandhelp;

import android.support.v4.app.Fragment;

/**
 * Created by xinghan on 3/29/15.
 */
public class MedicineListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new NewsListFragment();
    }
}
