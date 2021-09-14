package com.example.firebaseapp;

public class Student {

    private String ID;
    private String name;
    private String address;
    private Integer contactNO;

    public Student() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getContactNO() {
        return contactNO;
    }

    public void setContactNO(Integer contactNO) {
        this.contactNO = contactNO;
    }
}
