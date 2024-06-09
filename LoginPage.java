package com.hospital;

import com.hospital.services.AdminService;
import com.hospital.services.DoctorService;
import com.hospital.services.PatientService;
import com.hospital.models.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AdminService adminService;

    private JTextField idField;
    private JPasswordField passwordField;

    public LoginPage(DoctorService doctorService, PatientService patientService, AdminService adminService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.adminService = adminService;
    }

    public void display() {
        JFrame frame = new JFrame("HOSPITAL MANAGEMENT SYSTEM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel roleLabel = new JLabel("Select role:");
        JRadioButton doctorRadioButton = new JRadioButton("Doctor");
        JRadioButton patientRadioButton = new JRadioButton("Patient");
        JRadioButton adminRadioButton = new JRadioButton("Admin");
        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(doctorRadioButton);
        roleGroup.add(patientRadioButton);
        roleGroup.add(adminRadioButton);

        JPanel radioPanel = new JPanel();
        radioPanel.add(doctorRadioButton);
        radioPanel.add(patientRadioButton);
        radioPanel.add(adminRadioButton);

        JLabel idLabel = new JLabel("Enter ID:");
        idField = new JTextField();
        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordField = new JPasswordField();

        panel.add(roleLabel);
        panel.add(radioPanel);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        panel.add(new JPanel()); // Empty cell to align the login button
        panel.add(loginButton);

        doctorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setEnabled(true);
                passwordLabel.setText("Enter Password:");
                passwordField.setEchoChar('\u2022'); // Set password mask character
            }
        });

        patientRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setEnabled(true);
                passwordField.setText("");
                passwordLabel.setText("Enter Disease:");
                passwordField.setEchoChar((char) 0); // Make entered text visible
            }
        });

        adminRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setEnabled(true);
                passwordField.setText("");
                passwordLabel.setText("Enter Admin Password:");
                passwordField.setEchoChar('\u2022'); // Set password mask character
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int role = doctorRadioButton.isSelected() ? 1 : (patientRadioButton.isSelected() ? 2 : (adminRadioButton.isSelected() ? 3 : -1));
                String id = idField.getText();
                String passwordOrDisease = new String(passwordField.getPassword());

                try {
                    if (role == 1) {
                        Doctor doctor = doctorService.getDoctorByIdAndPassword(id, passwordOrDisease);
                        if (doctor != null) {
                            new DoctorPage(doctor).display();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid credentials for Doctor!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (role == 2) {
                        
                        // Patient login logic here
                    } else if (role == 3) {
                        if (adminService.authenticateAdmin(id, passwordOrDisease)) {
                            new AdminPage(adminService, doctorService, patientService).display();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid credentials for Admin!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid role selection!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "An error occurred while logging in.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
