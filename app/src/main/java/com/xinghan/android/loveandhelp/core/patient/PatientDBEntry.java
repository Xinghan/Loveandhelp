package com.xinghan.android.loveandhelp.core.patient;

import java.util.Date;

/**
 * Created by xinghan on 5/26/15.
 */
public class PatientDBEntry extends Patient{
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

    private int isDirty;
    private Date lastSync;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private int state;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    private String owner;
}
