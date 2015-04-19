package com.xinghan.android.loveandhelp.core.patient;

import java.util.Date;
import java.util.UUID;

/**
 * Created by xinghan on 4/18/15.
 */
public class Patient {
    private enum Gender {
        Male,
        Female
    };

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Gender getGender() {
        return mGender;
    }

    public void setGender(Gender gender) {
        this.mGender = gender;
    }

    public Date getBirthday() {
        return mBirthday;
    }

    public void setBirthday(Date birthday) {
        this.mBirthday = birthday;
    }

    public Integer getAge() {
        return mAge;
    }

    public void setAge(Integer age) {
        this.mAge = age;
    }

    public UUID getUuid() {
        return mUuid;
    }

    public void setUuid(UUID uuid) {
        mUuid = uuid;
    }

    private UUID mUuid;
    private String mName;
    private Gender mGender;
    private Date mBirthday;
    private Integer mAge;


    @Override
    public String toString() {
        return this.mName;
    }
}
