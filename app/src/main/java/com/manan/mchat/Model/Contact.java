package com.manan.mchat.Model;
public class Contact {
    //Class for Contact Model
    private String mName;
    private String mNumber;

    public Contact(String name, String number) {
        mName = name;
        mNumber = number;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }
}
