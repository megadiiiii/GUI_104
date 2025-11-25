package com.mycompany.w3.hw;

public class Person {
    String name;
    String dob;
    String address;
    String gender;

    public Person() {}

    public Person(String name, String dob, String address, String gender) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Ho ten:" + name + ", Ngay sinh: " + dob + ", Dia chi: " + address + ", Gioi tinh: " + gender;
    }
}
