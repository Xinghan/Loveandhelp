package com.xinghan.android.loveandhelp.core.patient;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by xinghan on 4/18/15.
 */
public class PatientLab {
    private ArrayList<Patient> mPatients;
    private static PatientLab sPatientLab;
    private Context mAppContext;

    PatientLab(Context context) {
        this.mAppContext = context;
        this.mPatients = new ArrayList<Patient>();
        for (int i=0; i<5; ++i) {
            Patient p = new Patient();
            p.setName("P" + i);
            mPatients.add(p);
        }
    }

    public static PatientLab getPatientLab(Context c) {
        if (sPatientLab == null) {
            sPatientLab = new PatientLab(c.getApplicationContext());
        }
        return sPatientLab;
    }

    public ArrayList<Patient> getPatients() {
        return mPatients;
    }

    public void addPatient(Patient patient) {
        mPatients.add(patient);
    }

    public Patient getPatient(UUID uuid) {
        for (Patient p: mPatients) {
            if (p.getUuid() == uuid) return p;
        }

        return null;
    }
}
