package com.xinghan.android.loveandhelp;

import android.support.v4.app.Fragment;

import com.xinghan.android.loveandhelp.ui.NewsListFragment;
import com.xinghan.android.loveandhelp.ui.SingleFragmentActivity;

/**
 * Created by xinghan on 3/29/15.
 */
public class MedicineListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NewsListFragment();
    }
}
