package com.xinghan.android.loveandhelp.core.patient;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by xinghan on 5/26/15.
 */
public class PatientSyncEvent {
    int status;
    public JSONObject result = null;
    PatientSyncEvent(JSONObject jo) {
        try {
            status = jo.getInt("status");
        } catch (Exception e) {

        }
        Log.d("patient sync", new Integer(status).toString());
        this.result = jo;
    }
}
