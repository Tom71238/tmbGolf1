package com.tbrey1gmail.tmbgolf.Data;

import java.util.ArrayList;



public class GCnames {
    //private String mGCname;
    private ArrayList<String> gcNames = new ArrayList<String>();

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    //public GCnames() {    }

    public void addGCname(String gc){
        gcNames.add(gc);
    }

    public ArrayList<String> getGCnames(){
        return gcNames;
    }
    public String getGCname(int loc){
        return gcNames.get(loc);
    }
    public int lengthOfArray(){
        return gcNames.size();
    }

}
