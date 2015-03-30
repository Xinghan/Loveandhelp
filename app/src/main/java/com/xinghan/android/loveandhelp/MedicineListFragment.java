package com.xinghan.android.loveandhelp;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by xinghan on 3/29/15.
 */
public class MedicineListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.news_title);
        mMedicine = MedicineLab.getMedicineLab(getActivity()).getMedicines();

        ArrayAdapter<Medicine> adapter =
                new ArrayAdapter<Medicine>(getActivity(),
                        android.R.layout.simple_list_item_1,
                        mMedicine);

        setListAdapter(adapter);
        setHasOptionsMenu(true);
    }

    private ArrayList<Medicine> mMedicine;
}
