package com.xinghan.android.loveandhelp.ui.patient;

import android.support.v4.app.Fragment;

import com.xinghan.android.loveandhelp.ui.SingleFragmentActivity;

/**
 * Created by xinghan on 4/29/15.
 */
public class PatientDetailsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PatientDetailsFragment();
    }
}
