package com.xinghan.android.loveandhelp.core.disease;

import java.util.UUID;

/**
 * Created by xinghan on 4/30/15.
 */
public class Disease {
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

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    @Override
    public String toString() {
        return mName;
    }

    private String mDescription;
    private UUID mUUID;
}
