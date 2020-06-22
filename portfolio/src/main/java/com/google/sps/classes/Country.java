package com.google.sps.classes;

import java.util.*;

public class Country {
    private String name;
    private String stringValue;
    private int intValue;

    public Country(String name, String value) {
       this.name = name; 
       this.stringValue = stringValue;
    }

    public String getName() {
        return name;
    }

    public String getStringValue() {
        return name;
    }

    public int getIntValue() {
       return (Integer.parseInt(stringValue)); 
    }
}