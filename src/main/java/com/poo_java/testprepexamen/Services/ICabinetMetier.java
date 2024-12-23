package com.poo_java.testprepexamen.Services;

import com.poo_java.testprepexamen.Entities.Consultation;
import com.poo_java.testprepexamen.Entities.Medecin;
import com.poo_java.testprepexamen.Entities.Patient;

import java.util.List;

public interface ICabinetMetier {
    // Methodes des patients de cabinet
    List<Patient> getPatients();
    Patient getPatient(int id);
    void addPatient(Patient patient);
    boolean deletePatient(int id);
    List<Consultation> getConsultationsByPatient(int idPatient);


    // Methodes des medecins
    void addMedecin(Medecin medecin);
    List<Medecin> getMedecins();
    boolean deleteMedecin(int idMedecin);
    List<Consultation> getConsultationsByMedecin(int idMedecin);

    // Methodes de consultations
    void addConsultation(Consultation consultation);
    List<Consultation> getConsultations();
    boolean deleteConsultation(int idConsultation);
}
