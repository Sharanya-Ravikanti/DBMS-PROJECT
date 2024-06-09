package com.hospital.models;

import java.util.List;

public class Doctor {
    private String id;
    private String password;
    private String name;
    private String visitTime;
    private List<Patient> patients;

    public Doctor(String id, String password, String name, String visitTime) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.visitTime = visitTime;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
