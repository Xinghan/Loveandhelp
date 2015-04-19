package com.xinghan.android.loveandhelp.ui.patient;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
        mPatients = PatientLab.getPatientLab(getActivity()).getPatients();

        PatientAdapter patientArrayAdapter = new PatientAdapter(mPatients);

        setListAdapter(patientArrayAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

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
