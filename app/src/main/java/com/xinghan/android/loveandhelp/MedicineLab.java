package com.xinghan.android.loveandhelp;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by xinghan on 3/29/15.
 */
public class MedicineLab {
    private ArrayList<Medicine> mMedicines;
    private Context mContext;
    public static MedicineLab sMedicineLab;

    private MedicineLab(Context context) {
        mContext = context;
        mMedicines = new ArrayList<Medicine>();
    }

    public static MedicineLab getMedicineLab(Context c) {
        if(sMedicineLab == null) {
            sMedicineLab = new MedicineLab(c.getApplicationContext());
        }
        return sMedicineLab;
    }

    public ArrayList<Medicine> getMedicines() {
        return mMedicines;
    }


}
