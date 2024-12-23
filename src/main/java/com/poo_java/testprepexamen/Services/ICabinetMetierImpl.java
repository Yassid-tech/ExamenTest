package com.poo_java.testprepexamen.Services;


import com.poo_java.testprepexamen.Config.SingletonConnexionDB;
import com.poo_java.testprepexamen.Entities.Consultation;
import com.poo_java.testprepexamen.Entities.Medecin;
import com.poo_java.testprepexamen.Entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ICabinetMetierImpl implements ICabinetMetier {

    private SingletonConnexionDB singletonConnexionDB = new SingletonConnexionDB();
    private Connection connection = singletonConnexionDB.getConnection();

    @Override
    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<Patient>();
        String query = "SELECT * FROM Patient";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()) {
                patients.add(new Patient(
                        resultSet.getInt("id_patient"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone"),
                        resultSet.getString("cin"),
                        resultSet.getDate("date_naissance")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public Patient getPatient(int id) {
        Patient patient = null;
        int idPatient = id;

        String query = "SELECT * FROM Patient WHERE id_patient = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, idPatient);
            try (ResultSet resultSet = ps.executeQuery()){
                if (resultSet.next()){
                    patient = new Patient(
                            resultSet.getInt("id_patient"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("email"),
                            resultSet.getString("telephone"),
                            resultSet.getString("cin"),
                            resultSet.getDate("date_naissance")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public void addPatient(Patient patient) {
        String query = "INSERT INTO Patient(nom, prenom, email, telephone, cin, date_naissance) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, patient.getNom());
            ps.setString(2, patient.getPrenom());
            ps.setString(3, patient.getEmail());
            ps.setString(4, patient.getTelephone());
            ps.setString(5, patient.getCin());
            ps.setDate(6,
                    new Date(patient.getDate_naissance().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deletePatient(int id) {
        String query = "DELETE FROM Patient WHERE id_patient = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Consultation> getConsultationsByPatient(int idPatient) {
        List<Consultation> consultations = new ArrayList<>();
        String query = "SELECT * FROM Consultation WHERE id_patient = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idPatient);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    consultations.add(new Consultation(
                            rs.getInt("id_consultation"),
                            rs.getDate("date_consultation"),
                            rs.getInt("id_patient"),
                            rs.getInt("id_medecin")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public void addMedecin(Medecin medecin) {
        String query = "INSERT INTO Medecin(nom, prenom, email, telephone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, medecin.getNom());
            ps.setString(2, medecin.getPrenom());
            ps.setString(3, medecin.getEmail());
            ps.setString(4, medecin.getTelephone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medecin> getMedecins() {
        List<Medecin> medecins = new ArrayList<Medecin>();
        String query = "SELECT * FROM Medecin";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()) {
                medecins.add(new Medecin(
                        resultSet.getInt("id_medecin"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("telephone")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medecins;
    }

    @Override
    public boolean deleteMedecin(int idMedecin) {
        String query = "DELETE FROM Medecin WHERE id_medecin = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idMedecin);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Consultation> getConsultationsByMedecin(int idMedecin) {
        List<Consultation> consultations = new ArrayList<>();
        String query = "SELECT * FROM Consultation WHERE id_medecin = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idMedecin);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    consultations.add(new Consultation(
                            rs.getInt("id_consultation"),
                            rs.getDate("date_consultation"),
                            rs.getInt("id_patient"),
                            rs.getInt("id_medecin")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public void addConsultation(Consultation consultation) {
        String query = "INSERT INTO Consultation(date_consultation, id_patient, id_medecin) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1,
                    new Date(consultation.getDateConsultation().getTime()));
            ps.setInt(2, consultation.getIdPatient());
            ps.setInt(3, consultation.getIdMedecin());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultation> getConsultations() {
        List<Consultation> consultations = new ArrayList<Consultation>();
        String query = "SELECT * FROM Consultation";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()) {
                consultations.add(new Consultation(
                        resultSet.getInt("id_consultation"),
                        resultSet.getDate("date_consultation"),
                        resultSet.getInt("id_patient"),
                        resultSet.getInt("id_medecin")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultations;
    }

    @Override
    public boolean deleteConsultation(int idConsultation) {
        String query = "DELETE FROM Consultation WHERE id_consultation = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idConsultation);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
