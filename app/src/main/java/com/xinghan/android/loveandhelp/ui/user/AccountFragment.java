package com.xinghan.android.loveandhelp.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.patient.PatientManager;
import com.xinghan.android.loveandhelp.ui.patient.PatientActivity;
import com.xinghan.android.loveandhelp.ui.patient.PatientFragment;

/**
 * Created by xinghan on 5/9/15.
 */
public class AccountFragment extends Fragment {
    public int LOGIN_CODE = 101;
    private Button mLoginButton;
    private Button mRegButton;
    private Button mLogoutButton;
    private ImageView mAvatar;
    private TextView mUsername;
    private String mUsernameText;

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
        mLoginButton = (Button)v.findViewById(R.id.login_button);
        mRegButton = (Button)v.findViewById(R.id.user_registration);
        mLogoutButton = (Button) v.findViewById(R.id.account_logout);
        mAvatar = (ImageView) v.findViewById(R.id.account_avatar);
        mUsername = (TextView) v.findViewById(R.id.account_username);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            ((ActionBarActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(i, LOGIN_CODE);
            }
        });

        mRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), UserActivity.class);
                startActivityForResult(i, LOGIN_CODE);
            }
        });

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("logout", "logout");
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove(getString(R.string.user_token));
                editor.remove(getString(R.string.user_name_text));
                editor.commit();
                setLoginUI();
            }
        });

        setLoginUI();
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == LOGIN_CODE) && (resultCode == Activity.RESULT_OK)) {
            setLoginUI();
        }
    }

    private void setLoginUI() {
        if (isLogedin()) {
            Log.d("Account", "loged in");
            mLoginButton.setVisibility(View.INVISIBLE);
            mRegButton.setVisibility(View.INVISIBLE);
            mAvatar.setVisibility(View.VISIBLE);
            mLogoutButton.setVisibility(View.VISIBLE);
            mUsername.setText(mUsernameText);
        }  else {
            mLoginButton.setVisibility(View.VISIBLE);
            mRegButton.setVisibility(View.VISIBLE);
            mAvatar.setVisibility(View.INVISIBLE);
            mUsername.setVisibility(View.INVISIBLE);
            mLogoutButton.setVisibility(View.INVISIBLE);
        }
    }

    private boolean isLogedin() {
        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getActivity());

        //SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "";
        String token = mSharedPreference.getString(getString(R.string.user_token), defaultValue);
        mUsernameText = mSharedPreference.getString(getString(R.string.user_name_text), defaultValue);
        return !token.isEmpty();
    }

}
