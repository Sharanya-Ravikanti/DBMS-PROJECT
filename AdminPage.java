package com.hospital;

import com.hospital.services.AdminService;
import com.hospital.services.DoctorService;
import com.hospital.services.PatientService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage {
    private final AdminService adminService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AdminPage(AdminService adminService, DoctorService doctorService, PatientService patientService) {
        this.adminService = adminService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public void display() {
        JFrame frame = new JFrame("Admin Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        // Doctor Management Button
        JButton manageDoctorsButton = new JButton("Manage Doctors");
        manageDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageDoctorPage(doctorService).display();
            }
        });
        panel.add(manageDoctorsButton);

        // Patient Management Button
        JButton managePatientsButton = new JButton("Manage Patients");
        managePatientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagePatientPage(patientService).display();
            }
        });
        panel.add(managePatientsButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
