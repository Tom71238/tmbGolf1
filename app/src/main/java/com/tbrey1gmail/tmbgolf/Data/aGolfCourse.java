package com.tbrey1gmail.tmbgolf.Data;

import java.util.ArrayList;

public class aGolfCourse {
    private String Name;
    private String PhoneNum;
    private ArrayList<String> TeeList = new ArrayList<>();
    private ArrayList<String> ParList = new ArrayList<>();
    private ArrayList <String> HdcList = new ArrayList<>();
    private ArrayList <String> TeeRatingList = new ArrayList<>();
    private ArrayList <String> TeeSlopeList = new ArrayList<>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public ArrayList<String> getTeeList() {
        return TeeList;
    }

    public void setTeeList(ArrayList<String> teeList) {
        TeeList = teeList;
    }

    public ArrayList<String> getParList() {
        return ParList;
    }

    public void setParList(ArrayList<String> parList) {
        ParList = parList;
    }

    public ArrayList<String> getHdcList() {
        return HdcList;
    }

    public void setHdcList(ArrayList<String> hdcList) {
        HdcList = hdcList;
    }

    public ArrayList<String> getTeeRatingList() {
        return TeeRatingList;
    }

    public void setTeeRatingList(ArrayList<String> teeRatingList) {
        TeeRatingList = teeRatingList;
    }

    public ArrayList<String> getTeeSlopeList() {
        return TeeSlopeList;
    }

    public void setTeeSlopeList(ArrayList<String> teeSlopeList) {
        TeeSlopeList = teeSlopeList;
    }
    //    public void setParListLong(ArrayList<Long> pList) {
//        for (Long l : pList) {
//            String s = Long.toString(l);
//            Integer i = Integer.parseInt(s);
//            ParList.add(i);
//        }
//    }
//
//    public void setHdcListLong(ArrayList<Long> hList) {
//
//        for (Long l : hList) {
//            String s = Long.toString(l);
//            Integer i = Integer.parseInt(s);
//            HdcList.add(i);
//        }
//    }
//
//    public void setTeeRatingListDouble(ArrayList<String> trList) {
//        for (Double d : trList) {
//            TeeRatingList.add(d);
//        }
//    }
//
//    public void setTeeSlopeListLong(ArrayList<String> tsList) {
//        for (Long l : tsList) {
//            String s = Long.toString(l);
//            Double i = Double.valueOf(s);
//            TeeSlopeList.add(i);
//        }
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public void setPhoneNum(String phoneNum) {
//        PhoneNum = phoneNum;
//    }
//
//    public void addTeeList(String tee, Integer idx) {
//        TeeList.add(idx,tee);
//    }
//
//    public void addParList(Integer par, Integer idx) {
//        ParList.add(idx,par);
//    }
//
//    public void addHdcList(Integer hdc, Integer idx) {
//        HdcList.add(idx, hdc);
//    }
//
//    public void addTeeRatingList(Double tr, Integer idx) {
//        TeeRatingList.add(idx,tr);
//    }
//
//
//    public void setTeeSlopeList(Double ts, Integer idx) {
//        TeeSlopeList.add(idx,ts);
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public String getPhoneNum() {
//        return PhoneNum;
//    }
//
//    public ArrayList<Integer> getParList() {
//        return ParList;
//    }
//
//    public ArrayList<Integer> getHdcList() {
//        return HdcList;
//    }
//
//    public ArrayList<String> getTeeList() {
//        return TeeList;
//    }
//
//    public ArrayList<Double> getTeeRatingList() {
//        return TeeRatingList;
//    }
//
//    public String getTeeRating_string(int pos){
//        return TeeRatingList.get(pos).toString();
//    }
//
//    public ArrayList<Double> getTeeSlopeList() {
//        return TeeSlopeList;
//    }
//
//    public String getTeeSlope_string(int pos) {
//        return TeeSlopeList.get(pos).toString();
//    }
}
