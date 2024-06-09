package com.hospital;

import com.hospital.services.AdminService;
import com.hospital.services.DoctorService;
import com.hospital.services.PatientService;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                DoctorService doctorService = new DoctorService();
                PatientService patientService = new PatientService();
                AdminService adminService = new AdminService();
                LoginPage loginPage = new LoginPage(doctorService, patientService, adminService);
                loginPage.display();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
