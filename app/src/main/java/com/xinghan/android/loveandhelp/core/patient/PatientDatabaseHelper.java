package com.xinghan.android.loveandhelp.core.patient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by xinghan on 4/19/15.
 */
public class PatientDatabaseHelper extends SQLiteOpenHelper {
    private final String LOG_TAG = "PATIENT_DB";
    private static final String PATIENT_DB_NAME = "patient.db";
    private static final int PATIENT_DB_VERSION = 1;

    private static final String TABLE_PATIENT = "patient";
    private static final String KEY_NAME = "name";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_UUID = "uuid";

    private static final String CREATE_PATIENT_TABLE =
            "CREATE TABLE patient (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "uuid, TEXT," +
                    "name, TEXT, " +
                    "birthday INTEGER)";

    PatientDatabaseHelper(Context context) {
        super(context, PATIENT_DB_NAME, null, PATIENT_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PATIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertPatient(Patient patient) {
        Log.i(LOG_TAG, "Add patient to database");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // Store UUID as string in database
        cv.put(KEY_UUID, patient.getUuid().toString());
        cv.put(KEY_NAME, patient.getName());
        cv.put(KEY_BIRTHDAY, patient.getBirthday().getTime());

        db.insert(TABLE_PATIENT, null, cv);

        db.close();
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<Patient>();

        String query = "SELECT * FROM " + TABLE_PATIENT;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Patient patient = null;
        if (cursor.moveToFirst()) {
            do {
                patient = new Patient();
                // The first field is UUID, the zero field is the db ID
                patient.setUuid(UUID.fromString(cursor.getString(1)));
                patient.setName(cursor.getString(2));
                patient.setBirthday(new Date(cursor.getString(3)));

                patients.add(patient);
            } while (cursor.moveToNext());
        }
        return patients;
    }
}

