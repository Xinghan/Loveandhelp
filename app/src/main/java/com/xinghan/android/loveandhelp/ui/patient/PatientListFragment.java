package com.xinghan.android.loveandhelp.ui.patient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xinghan.android.loveandhelp.R;
import com.xinghan.android.loveandhelp.core.patient.Patient;
import com.xinghan.android.loveandhelp.core.patient.PatientLab;
import com.xinghan.android.loveandhelp.core.patient.PatientManager;
import com.xinghan.android.loveandhelp.core.patient.PatientSync;
import com.xinghan.android.loveandhelp.core.patient.PatientSyncEvent;
import com.xinghan.android.loveandhelp.core.user.RegistrationEvent;
import com.xinghan.android.loveandhelp.core.user.UserSignupThread;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by xinghan on 4/18/15.
 */
public class PatientListFragment extends ListFragment{
    private ArrayList<Patient> mPatients;
    private PatientManager.PatientCursor mPatientCursor;
    private PatientCursorAdapter mCursorAdapter;

    public static final String EXTRA_PATIENT_ID = "com.xinghan.loveandhelp.patient.id";
    public int ADD_PATIENT_CODE = 100;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.patient_title);
        setHasOptionsMenu(true);
        mPatients = PatientLab.getPatientLab(getActivity()).getPatients();
        mPatientCursor = PatientManager.getPatientManager(getActivity()).queryPatients();
        mCursorAdapter = new PatientCursorAdapter(getActivity(), mPatientCursor);
        setListAdapter(mCursorAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        PatientManager.PatientCursor patientCursor =
                (PatientManager.PatientCursor)(mCursorAdapter.getItem(position));

        // Start PatientActivity
        Intent i = new Intent(getActivity(), PatientDetailsActivity.class);
        i.putExtra(EXTRA_PATIENT_ID, patientCursor.getLong(
                patientCursor.getColumnIndex(PatientManager.COLUMN_PATIENT_ID)));
        startActivity(i);
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    public void onEventMainThread(PatientSyncEvent event) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_patient:
                Patient patient = new Patient();
                PatientLab.getPatientLab(getActivity()).addPatient(patient);
                Intent i = new Intent(getActivity(), PatientActivity.class);
                startActivityForResult(i, ADD_PATIENT_CODE);
                return true;
            case R.id.menu_item_sync_patient:
                PatientSync n = new PatientSync(getActivity());
                n.execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == ADD_PATIENT_CODE) && (resultCode == Activity.RESULT_OK)) {
            mPatientCursor = PatientManager.getPatientManager(getActivity()).queryPatients();
            mCursorAdapter = new PatientCursorAdapter(getActivity(), mPatientCursor);
            setListAdapter(mCursorAdapter);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_patient_list, menu);
    }

    private static class PatientCursorAdapter extends CursorAdapter {
        private PatientManager.PatientCursor mPatientCursor;
        public PatientCursorAdapter(Context context, PatientManager.PatientCursor patientCursor) {
            super(context, patientCursor, 0);
            mPatientCursor = patientCursor;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return inflater
                    .inflate(R.layout.fragment_patientlist_row, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            Patient patient = mPatientCursor.getPatient();
            TextView patientNameTextView = (TextView)view.findViewById(R.id.patient_row_name_text);
            String patientName = patient.getName();
            patientNameTextView.setText(patientName);
        }
    }

}
