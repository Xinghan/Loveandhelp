package com.xinghan.android.loveandhelp.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xinghan on 4/22/15.
 */
public class LocalDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "loveandhelp.db";
    private static final int VERSION = 1;
    private static LocalDBHelper singletonDB = null;

    private static final String CREATE_PATIENT_TABLE =
            "CREATE TABLE patient (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "uuid, TEXT," +
                    "name, TEXT, " +
                    "birthday INTEGER)";

    private LocalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    synchronized static LocalDBHelper getSingletonDB(Context context) {
        if (singletonDB == null) {
            singletonDB = new LocalDBHelper(context.getApplicationContext());
        }

        return singletonDB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PATIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new RuntimeException("This should not be called");
    }
}
