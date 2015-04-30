package com.xinghan.android.loveandhelp.core.patient;

import com.xinghan.android.loveandhelp.core.Role;

import java.util.Date;
import java.util.UUID;

/**
 * Created by xinghan on 4/18/15.
 */
public class Patient extends Role {
    public enum Gender {
        Male,
        Female
    };

    public Patient() {
        this.mUuid = UUID.randomUUID();
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

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    private Gender mGender;
    private Date mBirthday;
    private Integer mAge;
    private long mId;

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String name) {
        mName = name;
    }

    private String mName;


    @Override
    public String toString() {
        return this.mName;
    }
}
