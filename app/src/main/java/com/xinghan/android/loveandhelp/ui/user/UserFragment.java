package com.xinghan.android.loveandhelp.ui.user;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.user.User;
import com.xinghan.android.loveandhelp.core.user.UserSignupThread;

/**
 * Created by xinghan on 4/17/15.
 */
public class UserFragment extends Fragment {
    Button mSignupButton;
    EditText mEmailText;
    EditText mPasswordText;
    EditText mUsernameText;

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
        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mSignupButton = (Button) v.findViewById(R.id.user_signup_button);
        mEmailText = (EditText) v.findViewById(R.id.user_email_editview);
        mPasswordText = (EditText) v.findViewById(R.id.user_password_editveiw);
        mUsernameText = (EditText) v.findViewById(R.id.user_name_editview);


        mSignupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = new User();
                user.setEmail(mEmailText.getText().toString());
                user.setName(mUsernameText.getText().toString());
                user.setPassword(mPasswordText.getText().toString());
                UserSignupThread thread = new UserSignupThread(user);
            }
        });

        return v;
    }

}
