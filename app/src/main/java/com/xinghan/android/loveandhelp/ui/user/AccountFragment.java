package com.xinghan.android.loveandhelp.ui.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.ui.patient.PatientActivity;
import com.xinghan.android.loveandhelp.ui.patient.PatientFragment;

/**
 * Created by xinghan on 5/9/15.
 */
public class AccountFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        Button loginButton = (Button)v.findViewById(R.id.login_button);
        Button registerButton = (Button)v.findViewById(R.id.user_registration);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(i, 0);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), UserActivity.class);
                startActivityForResult(i, 0);
            }
        });


        return v;
    }

}
