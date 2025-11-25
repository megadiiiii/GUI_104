package com.mycompany.w3.hw;

public class Student extends Person {
    String id;
    String email;

    public Student() {}

    public Student(String name, String dob, String address, String gender, String id, String email) {
        super(name, dob, address, gender);
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return super.toString() + ", MSV: " + id + ", Email: " + email;
    }
}
