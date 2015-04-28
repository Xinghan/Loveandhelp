package com.xinghan.android.loveandhelp.core.patient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private static final String COLUMN_PATIENT_ID = "id";
    private static final String COLUMN_PATIENT_NAME = "name";
    private static final String COLUMN_PATIENT_AGE = "age";


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
        //cv.put(KEY_BIRTHDAY, patient.getBirthday().getTime());

        db.insert(LocalDBHelper.TABLE_PATIENT, null, cv);

        db.close();
    }

    public PatientCursor queryPatients() {
        Cursor wrapped = mLocalDBHelper.getReadableDatabase().query(PATIENT_TABLE,
                null, null, null, null, null, COLUMN_PATIENT_ID + " asc");
        return new PatientCursor(wrapped);
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
            return patient;
        }
    }
}

