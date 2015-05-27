package com.xinghan.android.loveandhelp.core.patient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.xinghan.android.loveandhelp.network.ServerConnection;
import com.xinghan.android.loveandhelp.persistence.LocalDBHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by xinghan on 4/19/15.
 */
public class PatientManager {
    private static final String LOG_TAG = "PATIENT_DB";
    private static final String PATIENT_TABLE = "patient";
    public static final String COLUMN_PATIENT_ID = "_id";
    public static final String COLUMN_PATIENT_NAME = "name";
    public static final String COLUMN_PATIENT_AGE = "age";
    public static final String COLUMN_PATIENT_ISDIRTY = "isdirty";
    public static final String COLUMN_PATIENT_STATE = "state";


    private LocalDBHelper mLocalDBHelper;
    private static PatientManager sPatientManager;

    private PatientManager(Context c) {
        mLocalDBHelper = LocalDBHelper.getSingletonDB(c);
    }

    public static PatientManager getPatientManager(Context c) {
        if (sPatientManager == null) {
            sPatientManager = new PatientManager(c.getApplicationContext());
        }

        return sPatientManager;
    }

    public void insertPatient(Patient patient) {
        Log.i(LOG_TAG, "Add patient to database");

        SQLiteDatabase db = mLocalDBHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // Store UUID as string in database
        cv.put(LocalDBHelper.KEY_UUID, patient.getUuid().toString());
        cv.put(LocalDBHelper.KEY_NAME, patient.getName());
        cv.put(LocalDBHelper.KEY_AGE, patient.getAge());
        cv.put(LocalDBHelper.KEY_ISDIRTY, LocalDBHelper.IS_DIRTY);
        cv.put(LocalDBHelper.KEY_STATE, LocalDBHelper.NEW_ENTRY);
        //cv.put(KEY_BIRTHDAY, patient.getBirthday().getTime());

        db.insert(LocalDBHelper.TABLE_PATIENT, null, cv);

        db.close();
    }

    public PatientCursor queryPatients() {
        Cursor wrapped = mLocalDBHelper.getReadableDatabase().query(PATIENT_TABLE,
                null, null, null, null, null, COLUMN_PATIENT_ID + " asc");
        return new PatientCursor(wrapped);
    }

    public PatientCursor queryPatient(long id) {
        Cursor wrapped1 = mLocalDBHelper.getReadableDatabase().query(PATIENT_TABLE,
                null, null, null, null, null, COLUMN_PATIENT_ID + " asc");
        Log.d(LOG_TAG, "size: " + wrapped1.getCount());
        Cursor wrapped = mLocalDBHelper.getReadableDatabase().query(PATIENT_TABLE,
                new String[] { COLUMN_PATIENT_ID,
                        COLUMN_PATIENT_AGE, COLUMN_PATIENT_NAME }, // All columns
                COLUMN_PATIENT_ID +"="+ id, // Look for a run ID
                null, // with this value
                null, // group by
                null, // order by
                null); // limit 1 row
        Log.d(LOG_TAG, "s: " + wrapped.getCount());
        wrapped.moveToFirst();

        return new PatientCursor(wrapped);
    }

    public boolean updatePatient(Patient patient) {
        SQLiteDatabase db = mLocalDBHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // Store UUID as string in database
        cv.put(LocalDBHelper.KEY_UUID, patient.getUuid().toString());
        cv.put(LocalDBHelper.KEY_NAME, patient.getName());
        cv.put(LocalDBHelper.KEY_AGE, patient.getAge());
        cv.put(LocalDBHelper.KEY_ISDIRTY, LocalDBHelper.IS_DIRTY);
        cv.put(LocalDBHelper.KEY_STATE, LocalDBHelper.UPDATE_ENTRY);
        //cv.put(KEY_BIRTHDAY, patient.getBirthday().getTime());

        return db.update(PATIENT_TABLE, cv, COLUMN_PATIENT_ID + "=" + patient.getId(), null) > 0;
    }

    public ArrayList<PatientDBEntry> queryDirtyPatients() {
        Cursor wrapped = mLocalDBHelper.getReadableDatabase().query(PATIENT_TABLE,
                new String[] {COLUMN_PATIENT_ID,
                COLUMN_PATIENT_NAME, COLUMN_PATIENT_AGE, COLUMN_PATIENT_ISDIRTY, COLUMN_PATIENT_STATE},
                COLUMN_PATIENT_ISDIRTY + "=" + "1",
        null,
        null,
        null,
        null);

        wrapped.moveToFirst();
        ArrayList<PatientDBEntry> arrayList = new ArrayList<PatientDBEntry>();
        // Generate arraylist
        String owner = ServerConnection.SERVER + ServerConnection.RESTAPI + ServerConnection.ACCOUNTAPI;
        owner += "22/.json";

        while(!wrapped.isAfterLast()) {
            PatientDBEntry p = new PatientDBEntry();
            p.setName(wrapped.getString(wrapped.getColumnIndex("name")));
            p.setAge(wrapped.getInt(wrapped.getColumnIndex("age")));
            p.setIsDirty(wrapped.getInt(wrapped.getColumnIndex("isdirty")));
            p.setState(wrapped.getInt(wrapped.getColumnIndex("state")));
            p.setOwner(owner);
            arrayList.add(p);
            wrapped.moveToNext();
        }
        wrapped.close();

        return arrayList;
    }

    public static class PatientCursor extends CursorWrapper {

        public PatientCursor(Cursor c) {
            super(c);
        }

        public Patient getPatient() {
            if (isBeforeFirst() || isAfterLast())  return null;
            Patient patient = new Patient();
            long pId = getLong(getColumnIndex(COLUMN_PATIENT_ID));
            patient.setId(pId);
            int pAge = getInt(getColumnIndex(COLUMN_PATIENT_AGE));
            patient.setAge(new Integer(pAge));
            String pName = getString(getColumnIndex(COLUMN_PATIENT_NAME));
            patient.setName(pName);
            return patient;
        }
    }
}

