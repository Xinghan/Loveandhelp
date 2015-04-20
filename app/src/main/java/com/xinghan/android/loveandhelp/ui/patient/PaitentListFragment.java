package com.xinghan.android.loveandhelp.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.patient.Patient;
import com.xinghan.android.loveandhelp.core.patient.PatientLab;

import java.util.ArrayList;

/**
 * Created by xinghan on 4/18/15.
 */
public class PaitentListFragment extends ListFragment{
    private ArrayList<Patient> mPatients;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Patient");
        setHasOptionsMenu(true);
        mPatients = PatientLab.getPatientLab(getActivity()).getPatients();

        PatientAdapter patientArrayAdapter = new PatientAdapter(mPatients);

        setListAdapter(patientArrayAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Patient patient = new Patient();
                PatientLab.getPatientLab(getActivity()).addPatient(patient);
                Intent i = new Intent(getActivity(), PatientActivity.class);
                i.putExtra(PatientFragment.EXTRA_PATIENT_ID, patient.getUuid());
                startActivityForResult(i, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_patient_list, menu);
    }

    private class PatientAdapter extends ArrayAdapter<Patient> {
        PatientAdapter(ArrayList<Patient> patients) {
            super(getActivity(), 0, mPatients);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.fragment_patientlist_row, null);
            }

            Patient patient = getItem(position);
            TextView textView = (TextView)convertView.findViewById(R.id.patient_row_name_text);
            textView.setText(patient.toString());
            return convertView;
        }
    }
}
