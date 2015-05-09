package com.xinghan.android.loveandhelp.ui.user;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.news.NewsListLoadEvent;
import com.xinghan.android.loveandhelp.core.user.Register;
import com.xinghan.android.loveandhelp.core.user.RegistrationEvent;
import com.xinghan.android.loveandhelp.core.user.User;
import com.xinghan.android.loveandhelp.core.user.UserSignupThread;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;

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
                Register register = new Register();
                register.setEmail(mEmailText.getText().toString());
                register.setUsername(mUsernameText.getText().toString());
                register.setPassword(mPasswordText.getText().toString());

                UserSignupThread n = new UserSignupThread(register);
                n.execute();
            }
        });

        return v;
    }


    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    public void onEventMainThread(RegistrationEvent event) {
        Log.d("registration", "got event");
        if (event.result != null) {
            try {
                int status = event.result.getInt("status");
                Log.d("registration", new Integer(status).toString());
                if (status != 201) {
                    Toast.makeText(getActivity(), "Registration successful.", Toast.LENGTH_LONG)
                            .show();
                } else {
                    getActivity().finish();
                }
            } catch (Exception e) {
                Log.d("User fragment", e.getMessage());
            }
        }
        else {
            Log.d("registration", "no event");
        }
    }

}
