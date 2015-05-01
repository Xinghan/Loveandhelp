package com.xinghan.android.loveandhelp.core.Medicine;

import com.xinghan.android.loveandhelp.core.disease.Disease;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by xinghan on 4/30/15.
 */
public class Medicine {
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getManu() {
        return mManu;
    }

    public void setManu(String manu) {
        mManu = manu;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public ArrayList<Medicine> getRelatedMedicine() {
        return mRelatedMedicine;
    }

    public ArrayList<Disease> getRelatedDisease(){
        return mRelatedDisease;
    }

    private String mName;
    private String mDescription;
    private String mManu;
    private double mPrice;
    private UUID mUUID;
    private ArrayList<Medicine> mRelatedMedicine;
    private ArrayList<Disease> mRelatedDisease;

    @Override
    public String toString() {
        return mName;
    }
}
