package com.tbrey1gmail.tmbgolf.Data;

import java.util.ArrayList;


public class fake_gcName {
    private String m_gcName;

    public fake_gcName(String name) {
        m_gcName = name;
    }

    public String getName() {
        return m_gcName;
    }

    public static ArrayList<fake_gcName> createFakeGcNamesList(int num) {
        ArrayList<fake_gcName> gcNames = new ArrayList<>();
        for (int i = 0; i<num;i++) {
            gcNames.add(new fake_gcName("Golf Course Number " + i));
        }
        return gcNames;
    }
}
