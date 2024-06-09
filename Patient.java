package com.hospital.models;

public class Patient {
    private String id;
    private String disease;
    private String doctorId;
    private int age;
    private String phoneNumber;

    public Patient(String id, String disease, String doctorId, int age, String phoneNumber) {
        this.id = id;
        this.disease = disease;
        this.doctorId = doctorId;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getDisease() {
        return disease;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
