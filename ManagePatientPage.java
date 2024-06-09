package com.hospital;

import com.hospital.models.Patient;
import com.hospital.services.PatientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ManagePatientPage {
    private final PatientService patientService;

    public ManagePatientPage(PatientService patientService) {
        this.patientService = patientService;
    }

    public void display() {
        JFrame frame = new JFrame("Manage Patients");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("Patient ID:");
        JTextField idField = new JTextField(15);
        JLabel diseaseLabel = new JLabel("Disease:");
        JTextField diseaseField = new JTextField(15);
        JLabel doctorIdLabel = new JLabel("Doctor ID:");
        JTextField doctorIdField = new JTextField(15);
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(15);
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JTextField phoneNumberField = new JTextField(15);

        JButton insertButton = new JButton("Insert");
        JButton viewButton = new JButton("View");
        JButton deleteButton = new JButton("Delete");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(idLabel, gbc);

        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(diseaseLabel, gbc);

        gbc.gridx = 1;
        panel.add(diseaseField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(doctorIdLabel, gbc);

        gbc.gridx = 1;
        panel.add(doctorIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(ageLabel, gbc);

        gbc.gridx = 1;
        panel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(phoneNumberLabel, gbc);

        gbc.gridx = 1;
        panel.add(phoneNumberField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(insertButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        frame.add(panel, BorderLayout.NORTH);

        // Table to display patients
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Patient ID", "Disease", "Doctor ID", "Age", "Phone Number"}, 0);
        JTable patientTable = new JTable(tableModel);
        frame.add(new JScrollPane(patientTable), BorderLayout.CENTER);

        frame.setVisible(true);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String disease = diseaseField.getText();
                String doctorId = doctorIdField.getText();
                int age = Integer.parseInt(ageField.getText());
                String phoneNumber = phoneNumberField.getText();

                Patient patient = new Patient(id, disease, doctorId, age, phoneNumber);
                try {
                    patientService.insertPatient(patient);
                    JOptionPane.showMessageDialog(frame, "Patient inserted successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error inserting patient: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                try {
                    patientService.deletePatient(id);
                    JOptionPane.showMessageDialog(frame, "Patient deleted successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error deleting patient: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0); // Clear existing data
                try {
                    List<Patient> patients = patientService.getAllPatients();
                    for (Patient patient : patients) {
                        tableModel.addRow(new Object[]{patient.getId(), patient.getDisease(), patient.getDoctorId(), patient.getAge(), patient.getPhoneNumber()});
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error retrieving patients: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
