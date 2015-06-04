package com.xinghan.android.loveandhelp.core.patient;

import java.util.Date;

/**
 * Created by xinghan on 5/26/15.
 * PatientDBEntry is to present a single local SQLite
 * database entry of Patient
 */
public class PatientDBEntry extends Patient{
    private int state;
    private String owner;
    private int isDirty;
    private Date lastSync;

    public int getIsDirty() {
        return isDirty;
    }

    public void setIsDirty(int isDirty) {
        this.isDirty = isDirty;
    }

    public Date getLastSync() {
        return lastSync;
    }

    public void setLastSync(Date lastSync) {
        this.lastSync = lastSync;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
