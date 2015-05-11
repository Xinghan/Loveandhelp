package com.xinghan.android.loveandhelp.utils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by xinghan on 4/18/15.
 */
public class JsonUtils {
    private JSONArray mJSONArray;
    private JSONObject mJSONObject;
    private String mFilename;

    JsonUtils(JSONArray jsonArray) {
        this.mJSONArray = jsonArray;
    }
    JsonUtils(JSONObject jsonObject) {
        this.mJSONObject = jsonObject;
    }

    public void setFilename (String filename) {
        this.mFilename = filename;
    }

    public void saveJsonToFile() {


    }

}
