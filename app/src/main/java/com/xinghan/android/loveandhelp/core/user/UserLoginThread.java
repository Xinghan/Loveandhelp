package com.xinghan.android.loveandhelp.core.user;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 5/9/15.
 */
public class UserLoginThread extends AsyncTask<String, Void, JSONObject> {
    public final String url = "http://192.168.1.13:8000/api/api-token-auth/";

    private Login mLogin;
    public UserLoginThread(Login login) {
        this.mLogin = login;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject jo = getToken(url);
        return jo;
    }

    private JSONObject getToken(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        request.setHeader("Content-type", "application/json");
        request.addHeader("Accept", "application/json");
        JSONObject jo = null;
        boolean paramIsValid = true;

        if (paramIsValid) {
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer("");
            try {
                StringEntity entity = new StringEntity(new Gson().toJson(mLogin));
                request.setEntity(entity);
                BufferedReader br = new BufferedReader(new InputStreamReader(request.getEntity().getContent()));
                HttpResponse response = httpClient.execute(request);
                int status = response.getStatusLine().getStatusCode();
                Log.d("response code: ", (new Integer(status)).toString());
                bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line = "";
                String LineSeparator = System.getProperty("line.separator");
                while((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line + LineSeparator);
                }

                bufferedReader.close();

                Log.d("User http post:", "result: " + stringBuffer);

                jo = generateJSonObject(status, stringBuffer.toString());

                EventBus.getDefault().post(new RegistrationEvent(jo));

            } catch (Exception e){
                Log.d("User http thread", "post fail");
            }
        }

        return jo;
    }

    private JSONObject generateJSonObject(int status, String result) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("status", new Integer(status).toString());
            jo.put("result", result);
        } catch (JSONException e) {
            Log.d("user post", "Cannot gennerate JSON object");
        }

        return jo;
    }
}
