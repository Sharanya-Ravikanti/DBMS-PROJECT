package com.hospital.services;

import com.hospital.models.Doctor;
import com.hospital.models.Patient;
import com.hospital.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    public void insertDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO doctors (doctor_id, password, name, visittime) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getId());
            statement.setString(2, doctor.getPassword());
            statement.setString(3, doctor.getName());
            statement.setString(4, doctor.getVisitTime());
            statement.executeUpdate();
        }
    }

    public void deleteDoctor(String id) throws SQLException {
        String query = "DELETE FROM doctors WHERE doctor_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        }
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        String query = "SELECT * FROM doctors";
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Doctor doctor = new Doctor(
                        resultSet.getString("doctor_id"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("visittime")
                );
                doctor.setPatients(getPatientsByDoctorId(doctor.getId()));
                doctors.add(doctor);
            }
        }
        return doctors;
    }

    public Doctor getDoctorByIdAndPassword(String id, String password) throws SQLException {
        String query = "SELECT * FROM doctors WHERE doctor_id = ? AND password = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Doctor doctor = new Doctor(
                            resultSet.getString("doctor_id"),
                            resultSet.getString("password"),
                            resultSet.getString("name"),
                            resultSet.getString("visittime")
                    );
                    doctor.setPatients(getPatientsByDoctorId(doctor.getId()));
                    return doctor;
                } else {
                    return null;
                }
            }
        }
    }

    private List<Patient> getPatientsByDoctorId(String doctorId) throws SQLException {
        String query = "SELECT * FROM patients WHERE doctor_id = ?";
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctorId);
            try (ResultSet resultSet = statement.executeQuery()) {
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
        }
        return patients;
    }
}
