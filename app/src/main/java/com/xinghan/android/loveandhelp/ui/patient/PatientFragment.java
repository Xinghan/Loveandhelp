package com.xinghan.android.loveandhelp.ui.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.patient.Patient;
import com.xinghan.android.loveandhelp.core.patient.PatientManager;

/**
 * Created by xinghan on 4/18/15.
 */
public class PatientFragment extends Fragment{
    public static final String EXTRA_PATIENT_ID = "com.xinghan.android.loveandhelp.ui.patient";

    private PatientManager mPatientManager;

    private Button mAddPatientButton;
    private EditText mPatientAge;
    private EditText mPatientName;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        mPatientManager = PatientManager.getPatientManager(getActivity());
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_details, container, false);
        mAddPatientButton = (Button)v.findViewById(R.id.patient_add_button);
        mPatientAge = (EditText)v.findViewById(R.id.patient_age_edit);
        mPatientName = (EditText)v.findViewById(R.id.patient_name_edit);
        mAddPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Patient patient = new Patient();
                patient.setAge(new Integer(mPatientAge.getText().toString()));
                patient.setName(mPatientName.getText().toString());
                mPatientManager.insertPatient(patient);
            }
        });

        return v;
    }

}
