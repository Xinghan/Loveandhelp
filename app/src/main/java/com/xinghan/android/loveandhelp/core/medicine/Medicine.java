package com.xinghan.android.loveandhelp.core.medicine;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by xinghan on 5/2/15.
 */
public class Medicine {
    private String mName;

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

    public String getManufacturer() {
        return mManufacturer;
    }

    public void setManufacturer(String manufacturer) {
        mManufacturer = manufacturer;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public ArrayList<Medicine> getRelatedMedicine() {
        return mRelatedMedicine;
    }

    public void setRelatedMedicine(ArrayList<Medicine> relatedMedicine) {
        mRelatedMedicine = relatedMedicine;
    }

    private String mDescription;
    private String mManufacturer;
    private UUID mUUID;
    private double mPrice;
    private ArrayList<Medicine> mRelatedMedicine;


}
