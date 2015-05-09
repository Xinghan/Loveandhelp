package com.xinghan.android.loveandhelp.core.user;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by xinghan on 5/9/15.
 */
public class RegistrationEvent {
    int status;
    public JSONObject result = null;
    RegistrationEvent(JSONObject jo) {
        try {
            status = jo.getInt("status");
        } catch (Exception e) {

        }
        Log.d("register event", new Integer(status).toString());
        this.result = jo;
    }
}
