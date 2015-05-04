package com.xinghan.android.loveandhelp.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.patient.Patient;
import com.xinghan.android.loveandhelp.core.patient.PatientLab;
import com.xinghan.android.loveandhelp.core.patient.PatientManager;

import org.w3c.dom.Text;

/**
 * Created by xinghan on 4/29/15.
 */
public class PatientDetailsFragment extends Fragment {
    private PatientManager mPatientManager;
    private Patient mPatient;
    private TextView mPatientNameText;
    private TextView mPatientAgeText;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        mPatientManager = PatientManager.getPatientManager(getActivity());
        if (getActivity().getIntent().getExtras() != null) {
            long pId = (long)(getActivity().getIntent()
                    .getSerializableExtra(PatientListFragment.EXTRA_PATIENT_ID));

            PatientManager.PatientCursor patientCursor = mPatientManager.queryPatient(pId);
            if(patientCursor.getPatient() != null) {
                mPatient = patientCursor.getPatient();
            }
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_details, container, false);
        mPatientAgeText = (TextView) v.findViewById(R.id.patient_details_age);
        mPatientNameText = (TextView) v.findViewById(R.id.patient_details_name);
        ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (mPatient != null) {
            mPatientNameText.setText(mPatient.getName());
            mPatientAgeText.setText(mPatient.getAge().toString());
        }

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_patient_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_patient:
                Intent i = new Intent(getActivity(), PatientActivity.class);
                i.putExtra(PatientFragment.EXTRA_PATIENT_ID, mPatient.getId());
                startActivityForResult(i, 0);
                return true;
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
