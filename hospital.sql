CREATE DATABASE hospital_db;

USE hospital_db;

CREATE TABLE doctors (
    doctor_id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50),
    name VARCHAR(100),
    visittime TIME
);

CREATE TABLE patients (
    patient_id VARCHAR(50) PRIMARY KEY,
    disease VARCHAR(100),
    doctor_id VARCHAR(50),
     age INT,
     phone VARCHAR(20),
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);
CREATE TABLE admin (
        id VARCHAR(50) PRIMARY KEY,
        password VARCHAR(50)
    );