package com.xinghan.android.loveandhelp.core;

import java.util.UUID;

/**
 * Created by xinghan on 4/19/15.
 */
public  class Role {
    public UUID getUuid() {
        return mUuid;
    }

    public void setUuid(UUID uuid) {
        mUuid = uuid;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    protected UUID mUuid;
    protected String mName;


}
