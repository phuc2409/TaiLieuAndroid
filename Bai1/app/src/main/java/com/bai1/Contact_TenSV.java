package com.bai1;

import java.io.Serializable;

public class Contact_TenSV implements Comparable<Contact_TenSV>, Serializable {
    private int id;
    private String name;
    private String phoneNumber;

    public Contact_TenSV(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Contact_TenSV contact_tenSV) {
        return this.name.compareTo(contact_tenSV.name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
