package com.xinghan.android.loveandhelp.core.user;

import java.util.UUID;

/**
 * Created by xinghan on 4/17/15.
 */
public class User {
    private UUID mUUID;

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    private String mUsername;
    private String mPassword;
}
