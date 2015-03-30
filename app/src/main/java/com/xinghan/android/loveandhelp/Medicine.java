package com.xinghan.android.loveandhelp;

import java.util.UUID;

/**
 * Created by xinghan on 3/29/15.
 */
public class Medicine {
    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDetails() {
        return mDetails;
    }

    public String getCompany() {
        return mCompany;
    }

    public String getCountry() {
        return mCountry;
    }

    private UUID mId;

    public void setId(UUID id) {
        mId = id;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setDetails(String details) {
        this.mDetails = details;
    }

    public void setCompany(String company) {
        this.mCompany = company;
    }

    public void setCountry(String country) {
        this.mCountry = country;
    }

    public String toString() {
        return mName;
    }
    private String mName;
    private String mDescription;
    private String mDetails;
    private String mCompany;
    private String mCountry;

}
