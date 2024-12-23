package com.poo_java.testprepexamen.Entities;

import java.util.Date;

public class Consultation {
    private int idConsultation;
    private Date dateConsultation;
    private int idPatient;
    private int idMedecin;

    public Consultation(int idConsultation, Date dateConsultation, int idPatient, int idMedecin) {
        this.idConsultation = idConsultation;
        this.dateConsultation = dateConsultation;
        this.idPatient = idPatient;
        this.idMedecin = idMedecin;
    }

    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "idConsultation=" + idConsultation +
                ", dateConsultation=" + dateConsultation +
                ", idPatient=" + idPatient +
                ", idMedecin=" + idMedecin +
                '}';
    }
}
