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
import com.xinghan.android.loveandhelp.core.user.Login;
import com.xinghan.android.loveandhelp.core.user.LoginEvent;
import com.xinghan.android.loveandhelp.core.user.Register;
import com.xinghan.android.loveandhelp.core.user.RegistrationEvent;
import com.xinghan.android.loveandhelp.core.user.UserLoginThread;
import com.xinghan.android.loveandhelp.core.user.UserSignupThread;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 5/9/15.
 */
public class LoginFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        setHasOptionsMenu(true);
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

    public void onEventMainThread(LoginEvent event) {
        Log.d("registration", "got event");
        if (event.result != null) {
            try {
                int status = event.result.getInt("status");
                Log.d("login", new Integer(status).toString());
                if (status != 201) {
                    Toast.makeText(getActivity(), "Login Failed.", Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getActivity(), "Login successful.", Toast.LENGTH_LONG)
                            .show();
                    getActivity().finish();
                }
            } catch (Exception e) {
                Log.d("User fragment", e.getMessage());
            }
        }
        else {
            Log.d("login", "no event");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button loginButton = (Button)v.findViewById(R.id.user_login_button);
        final EditText usernameEditText = (EditText) v.findViewById(R.id.edit_login_username);
        final EditText passwordEditText = (EditText) v.findViewById(R.id.edit_login_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login login = new Login();

                login.setUsername(usernameEditText.getText().toString());
                login.setPassword(passwordEditText.getText().toString());

                UserLoginThread n = new UserLoginThread(login);
                n.execute();
            }
        });

        return v;
    }
}
