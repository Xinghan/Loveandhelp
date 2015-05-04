package com.xinghan.android.loveandhelp.ui.patient;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.patient.Patient;
import com.xinghan.android.loveandhelp.core.patient.PatientManager;

/**
 * Created by xinghan on 4/18/15.
 */
public class PatientFragment extends Fragment{
    public static final String EXTRA_PATIENT_ID = "com.xinghan.android.loveandhelp.ui.patient";
    private static final String TAG = "PatientFragment";

    private PatientManager mPatientManager;

    private Button mAddPatientButton;
    private EditText mPatientAge;
    private EditText mPatientName;
    private Patient mPatient;
    private Boolean isUpdate = false;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        mPatientManager = PatientManager.getPatientManager(getActivity());
        if (getActivity().getIntent().getExtras() != null) {
            long pId = (long)(getActivity().getIntent()
                    .getSerializableExtra(PatientFragment.EXTRA_PATIENT_ID));

            PatientManager.PatientCursor patientCursor = mPatientManager.queryPatient(pId);
            if(patientCursor.getPatient() != null) {
                mPatient = patientCursor.getPatient();
                isUpdate = true;
            }
        }

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_edit, container, false);

        ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAddPatientButton = (Button)v.findViewById(R.id.patient_add_button);
        mPatientAge = (EditText)v.findViewById(R.id.patient_age_edit);
        mPatientName = (EditText)v.findViewById(R.id.patient_name_edit);

        if (mPatient != null) {
            mPatientName.setText(mPatient.getName());
            mPatientAge.setText(mPatient.getAge().toString());
        }

        mAddPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Patient patient = new Patient();
                String patientAge = mPatientAge.getText().toString();
                String patientName = mPatientName.getText().toString();
                patient.setAge(new Integer(mPatientAge.getText().toString()));
                patient.setName(mPatientName.getText().toString());

                if (patientName == null) {
                    PatientDialog pd = new PatientDialog();
                    pd.show(getActivity().getFragmentManager(), TAG);
                }
                if (patientAge == null) {
                    PatientDialog pd = new PatientDialog();
                    pd.show(getActivity().getFragmentManager(), TAG);
                }

                if (isUpdate) {
                    patient.setId(mPatient.getId());
                    if (mPatientManager.updatePatient(patient)) {
                        // Toast successfully update
                        Toast.makeText(getActivity(), "Update successful.", Toast.LENGTH_LONG)
                                .show();
                        getActivity().finish();

                    }
                } else {
                    mPatientManager.insertPatient(patient);
                    Toast.makeText(getActivity(), "Save successful.", Toast.LENGTH_LONG)
                            .show();
                    getActivity().finish();
                }
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_patient_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
