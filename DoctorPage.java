package com.hospital;

import com.hospital.models.Doctor;
import com.hospital.models.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DoctorPage {
    private final Doctor doctor;

    public DoctorPage(Doctor doctor) {
        this.doctor = doctor;
    }

    public void display() {
        JFrame frame = new JFrame("Doctor Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome, Dr. " + doctor.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Patient ID", "Disease", "Age", "Phone Number"}, 0);
        JTable table = new JTable(tableModel);
        
        List<Patient> patients = doctor.getPatients();
        if (patients != null) {
            for (Patient patient : patients) {
                tableModel.addRow(new Object[]{patient.getId(), patient.getDisease(), patient.getAge(), patient.getPhoneNumber()});
            }
        } else {
            System.err.println("No patients found for Doctor: " + doctor.getName());
        }

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }
}
