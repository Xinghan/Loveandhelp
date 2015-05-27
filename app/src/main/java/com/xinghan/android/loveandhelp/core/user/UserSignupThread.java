package com.xinghan.android.loveandhelp.core.user;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.xinghan.android.loveandhelp.network.ServerConnection;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 5/5/15.
 */
public class UserSignupThread extends AsyncTask<String, Void, JSONObject> {
    public final int REGISTRATION = 1;
    public final String url = ServerConnection.SERVER + ServerConnection.RESTAPI + ServerConnection.ACCOUNTAPI;

    private Register mRegister;

    public UserSignupThread(Register register) {
        this.mRegister = register;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject jo = getHttpJsonResult(url, REGISTRATION);
        return jo;
    }

    @Override
    protected void onPostExecute(JSONObject jo) {
        super.onPostExecute(jo);
    }

    private JSONObject getHttpJsonResult(String url, int state) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        request.setHeader("Content-type", "application/json");
        request.addHeader("Accept", "application/json");
        JSONObject jo = null;
        boolean paramIsValid = false;

        switch(state){
            case REGISTRATION:
                paramIsValid = true;
                break;
            default:
                Log.d("User Register Thread", "Invalid User state");
        }

        if (paramIsValid) {
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer("");
            try {
                Log.d("Signup", new Gson().toJson(mRegister));
                StringEntity entity = new StringEntity(new Gson().toJson(mRegister));

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
