package com.xinghan.android.loveandhelp.ui.patient;

import android.support.v4.app.Fragment;

import com.xinghan.android.loveandhelp.ui.SingleFragmentActivity;
import com.xinghan.android.loveandhelp.ui.news.NewsFragment;

/**
 * Created by xinghan on 4/18/15.
 */
public class PatientActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PatientFragment();
    }
}
