package com.tbrey1gmail.tmbgolf.Data;

import java.util.ArrayList;

/**
 * Created by tom on 1/24/2018.
 */

public class list_GolfCourses {
    private String gcName;
    private String phoneNumber;
    private ArrayList<Integer> teeLengths = new ArrayList<>();
    //ToDo: want to provide an image of the user


    public list_GolfCourses(String gcName, String phoneNumber, Integer teeLong, Integer teeLongPlus1, Integer teeLongPlus2, Integer teeLongPlus3) {
        this.gcName = gcName;
        this.phoneNumber = phoneNumber;
        this.teeLengths.add(teeLong);
        this.teeLengths.add(teeLongPlus1);
        this.teeLengths.add(teeLongPlus2);
        this.teeLengths.add(teeLongPlus3);
    }

    public String getGcName() {
        return gcName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Integer> getTees() {
        return teeLengths;
    }

}
