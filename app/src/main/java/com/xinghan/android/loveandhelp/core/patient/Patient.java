package com.xinghan.android.loveandhelp.core.patient;

import java.util.Date;

/**
 * Created by xinghan on 4/18/15.
 */
public class Patient {
    private enum Gender {
        Male,
        Female
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String name;
    private Gender gender;
    private Date birthday;
    private Integer age;

    @Override
    public String toString() {
        return this.name;
    }
}
