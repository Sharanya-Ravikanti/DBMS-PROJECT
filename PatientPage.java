package com.hospital;

import com.hospital.models.Patient;

import javax.swing.*;
import java.awt.*;

public class PatientPage {
    private final Patient patient;

    public PatientPage(Patient patient) {
        this.patient = patient;
    }

    public void display() {
        JFrame frame = new JFrame("Patient Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel idLabel = new JLabel("Patient ID: " + patient.getId());
        JLabel diseaseLabel = new JLabel("Disease: " + patient.getDisease());
        JLabel doctorLabel = new JLabel("Doctor ID: " + patient.getDoctorId());
        JLabel ageLabel = new JLabel("Age: " + patient.getAge());
        
        // Assuming the phone number is stored as a field named 'phoneNumber'
        JLabel phoneLabel = new JLabel("Phone: " + patient.getPhoneNumber());

        panel.add(idLabel);
        panel.add(diseaseLabel);
        panel.add(doctorLabel);
        panel.add(ageLabel);
        panel.add(phoneLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
