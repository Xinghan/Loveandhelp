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

    private Gender mGender;
    private Date mBirthday;
    private Integer mAge;


    @Override
    public String toString() {
        return this.mName;
    }
}
