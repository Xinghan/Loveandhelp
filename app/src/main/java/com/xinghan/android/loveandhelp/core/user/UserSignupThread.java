package com.xinghan.android.loveandhelp.core.user;

import android.util.Log;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by xinghan on 5/5/15.
 */
public class UserSignupThread extends Thread {

    private User mUser;

    public UserSignupThread(User user) {
        this.mUser = user;
    }

    @Override
    public void run() {
        Gson userGson = new Gson();
        String url = "http://192.168.1.11:8000/api/account";
        DataOutputStream printout;
        DataInputStream input;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();

            printout = new DataOutputStream(conn.getOutputStream ());
            printout.writeBytes(URLEncoder.encode(userGson.toJson(mUser), "UTF-8"));
            printout.flush();
            printout.close();
        } catch (Exception e){
            Log.d("UserSignup", "cannot signup user");
        }
    }
}
