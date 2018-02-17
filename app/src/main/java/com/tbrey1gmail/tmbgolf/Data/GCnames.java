package com.tbrey1gmail.tmbgolf.Data;

import java.util.ArrayList;



public class GCnames {
    //private String mGCname;
    private ArrayList<String> gcNames = new ArrayList<String>();
    private ArrayList<Integer> gcNamesFBindex = new ArrayList<Integer>();

    public void addGCname(String gc){
        gcNames.add(gc);
    }
    public void addGCindex(Integer gcIdx){
        gcNamesFBindex.add(gcIdx);
    }

    public ArrayList<String> getGCnames(){
        return gcNames;
    }

    public ArrayList<Integer> getGcNamesFBindex(){
        return gcNamesFBindex;
    }

    public String getGCname(int loc){
        return gcNames.get(loc);
    }

    public int lengthOfArray(){

        return gcNames.size();
    }
}
