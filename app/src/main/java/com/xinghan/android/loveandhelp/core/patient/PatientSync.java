package com.xinghan.android.loveandhelp.core.patient;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.network.ServerConnection;
import com.xinghan.android.loveandhelp.persistence.LocalDBHelper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 4/30/15.
 */
public class PatientSync extends AsyncTask<String, Void, JSONObject> {
    public final int REGISTRATION = 1;
    public final String url = ServerConnection.SERVER + ServerConnection.RESTAPI + ServerConnection.PATIENTAPI;

    private Patient mPatient;
    private Context mContext;
    private static PatientSync sPatientSync;

    /**
     * Constructor of PatientSync
     * @param context
     */
    private PatientSync(Context context) {
        this.mContext = context;
    }

    /**
     * Static method to get PatientSync object
     * @param c
     * @return PatientSync object
     */
    public static PatientSync getPatientSync(Context c) {
        if(sPatientSync == null) {
            sPatientSync = new PatientSync(c);
        }

        return sPatientSync;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject jo = getHttpJsonResult(url);
        return jo;
    }

    @Override
    protected void onPostExecute(JSONObject jo) {
        super.onPostExecute(jo);
    }

    /**
     * Parse JSON result from server
     * @param url
     * @return A JSON object represent
     */
    private JSONObject getHttpJsonResult(String url) {
        HttpClient httpClient = new DefaultHttpClient();

        // Http POST request
        HttpPost postRequest = new HttpPost(url);
        postRequest.setHeader("Content-type", "application/json");
        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(mContext);
        //SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "";
        String token = mSharedPreference.getString(mContext.getString(R.string.user_token), defaultValue);
        token = "JWT " + token;
        postRequest.addHeader("Authorization", token);

        // Http PUT request
        HttpPut putRequest = new HttpPut(url);
        putRequest.setHeader("Content-type", "application/json");
        putRequest.addHeader("Authorization", token);

        JSONObject jo = null;
        boolean paramIsValid = false;

        paramIsValid = true;
        if (paramIsValid) {
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer("");
            PatientManager pm = PatientManager.getPatientManager(mContext);
            ArrayList<PatientDBEntry> patientDBEntryArrayList = pm.queryDirtyPatients();
            try {
                for (PatientDBEntry pe: patientDBEntryArrayList) {
                    JSONObject obj = new JSONObject();
                    obj.put("name", pe.getName());
                    obj.put("age", pe.getAge());
                    obj.put("owner", pe.getOwner());
                    String pes = obj.toString();
                    StringEntity entity = new StringEntity(pes);

                    // Entry is a new entry
                    if(pe.getState() == LocalDBHelper.NEW_ENTRY) {
                        postRequest.setEntity(entity);
                        BufferedReader br = new BufferedReader(new InputStreamReader(postRequest.getEntity().getContent()));
                    } else if(pe.getState() == LocalDBHelper.UPDATE_ENTRY) {
                        // The entry is an update entry
                        postRequest.setEntity(entity);
                        BufferedReader br = new BufferedReader(new InputStreamReader(postRequest.getEntity().getContent()));
                    } else if(pe.getState() == LocalDBHelper.DELETE_ENTRY) {
                        // The entry is deleted
                        // Http DELETE request
                        HttpDelete deleteRequest = new HttpDelete(url);
                        putRequest.setHeader("Content-type", "application/json");
                        putRequest.addHeader("Authorization", token);
                    }

                    HttpResponse response = httpClient.execute(postRequest);
                    int status = response.getStatusLine().getStatusCode();
                    Log.d("response code: ", (new Integer(status)).toString());
                    bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    String line = "";
                    String LineSeparator = System.getProperty("line.separator");
                    while((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line + LineSeparator);
                    }

                    bufferedReader.close();
                    jo = generateJSonObject(status, stringBuffer.toString());
                    EventBus.getDefault().post(new PatientSyncEvent(jo));
                }

            } catch (Exception e){
                Log.d("User http thread", "post fail");
            }
        }

        return jo;
    }

    /**
     * Generate a JSON object for the result
     * @param status
     * @param result
     * @return A JSON object
     */
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

