package com.xinghan.android.loveandhelp.core.user;

import com.xinghan.android.loveandhelp.core.Role;

import java.util.UUID;

/**
 * Created by xinghan on 4/17/15.
 */
public class User extends Role {
    private String mPassword;
    private String mEmail;

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }
}
