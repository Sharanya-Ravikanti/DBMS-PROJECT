package com.hospital.services;

import com.hospital.models.Patient;
import com.hospital.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientService {

    public void insertPatient(Patient patient) throws SQLException {
        String query = "INSERT INTO patients (patient_id, disease, doctor_id, age, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getId());
            statement.setString(2, patient.getDisease());
            statement.setString(3, patient.getDoctorId());
            statement.setInt(4, patient.getAge());
            statement.setString(5, patient.getPhoneNumber());
            statement.executeUpdate();
        }
    }

    public void deletePatient(String id) throws SQLException {
        String query = "DELETE FROM patients WHERE patient_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        }
    }

    public List<Patient> getAllPatients() throws SQLException {
        String query = "SELECT * FROM patients";
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getString("patient_id"),
                        resultSet.getString("disease"),
                        resultSet.getString("doctor_id"),
                        resultSet.getInt("age"),
                        resultSet.getString("phone")
                );
                patients.add(patient);
            }
        }
        return patients;
    }
}
