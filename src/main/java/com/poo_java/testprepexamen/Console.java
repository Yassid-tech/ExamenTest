package com.poo_java.testprepexamen;

import com.poo_java.testprepexamen.Entities.Consultation;
import com.poo_java.testprepexamen.Entities.Medecin;
import com.poo_java.testprepexamen.Entities.Patient;
import com.poo_java.testprepexamen.Services.ICabinetMetier;
import com.poo_java.testprepexamen.Services.ICabinetMetierImpl;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) throws ParseException {
        ICabinetMetierImpl cabinetMetier = new ICabinetMetierImpl();
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Afficher tous les patients");
            System.out.println("2. Ajouter un patient");
            System.out.println("3. Supprimer un patient");
            System.out.println("4. Rechercher un patient");
            System.out.println("5. Afficher toutes les consultations");
            System.out.println("6. Ajouter une consultation");
            System.out.println("7. Supprimer une consultation");
            System.out.println("8. Afficher tous les médecins");
            System.out.println("9. Ajouter un médecin");
            System.out.println("10. Supprimer un médecin");
            System.out.println("11. Rechercher une consultation par patient");
            System.out.println("12. Rechercher une consultation par medecin");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    List<Patient> patients = cabinetMetier.getPatients();
                    patients.forEach(System.out::println);
                    break;

                case 2:
                    System.out.print("Nom : ");
                    String nom = scanner.next();
                    System.out.print("Prénom : ");
                    String prenom = scanner.next();
                    System.out.print("Email : ");
                    String email = scanner.next();
                    System.out.print("Téléphone : ");
                    String telephone = scanner.next();
                    System.out.print("CIN : ");
                    String cin = scanner.next();
                    System.out.print("Date de naissance (yyyy-MM-dd) : ");
                    String dateNaissance = scanner.next();
                    Patient patient = new Patient(0, nom, prenom, email, telephone, cin, Date.valueOf(dateNaissance));
                    cabinetMetier.addPatient(patient);
                    System.out.println("Patient ajouté avec succès !");
                    break;

                case 3:
                    System.out.print("ID du patient à supprimer : ");
                    int idPatient = scanner.nextInt();
                    if (cabinetMetier.deletePatient(idPatient)) {
                        System.out.println("Patient supprimé avec succès !");
                    } else {
                        System.out.println("Erreur lors de la suppression !");
                    }
                    break;

                case 4:
                    System.out.print("ID du patient à rechercher : ");
                    int idRecherche = scanner.nextInt();
                    Patient p = cabinetMetier.getPatient(idRecherche);
                    if (p != null) {
                        System.out.println(p);
                    } else {
                        System.out.println("Patient introuvable !");
                    }
                    break;

                case 5:
                    List<Consultation> consultations = cabinetMetier.getConsultations();
                    consultations.forEach(System.out::println);
                    break;

                case 6:
                    System.out.print("Date de consultation (yyyy-MM-dd) : ");
                    String dateConsultation = scanner.next();
                    System.out.print("ID du patient : ");
                    int idPatientConsultation = scanner.nextInt();
                    System.out.print("ID du médecin : ");
                    int idMedecin = scanner.nextInt();
                    Consultation consultation = new Consultation(0, Date.valueOf(dateConsultation), idPatientConsultation, idMedecin);
                    cabinetMetier.addConsultation(consultation);
                    System.out.println("Consultation ajoutée avec succès !");
                    break;

                case 7:
                    System.out.print("ID de la consultation à supprimer : ");
                    int idConsultation = scanner.nextInt();
                    if (cabinetMetier.deleteConsultation(idConsultation)) {
                        System.out.println("Consultation supprimée avec succès !");
                    } else {
                        System.out.println("Erreur lors de la suppression !");
                    }
                    break;

                case 8:
                    List<Medecin> medecins = cabinetMetier.getMedecins();
                    medecins.forEach(System.out::println);
                    break;

                case 9:
                    System.out.print("Nom : ");
                    String nomMedecin = scanner.next();
                    System.out.print("Prénom : ");
                    String prenomMedecin = scanner.next();
                    System.out.print("Email : ");
                    String emailMedecin = scanner.next();
                    System.out.print("Téléphone : ");
                    String telephoneMedecin = scanner.next();
                    Medecin medecin = new Medecin(0, nomMedecin, prenomMedecin, emailMedecin, telephoneMedecin);
                    cabinetMetier.addMedecin(medecin);
                    System.out.println("Médecin ajouté avec succès !");
                    break;

                case 10:
                    System.out.print("ID du médecin à supprimer : ");
                    int idMedecinSupprimer = scanner.nextInt();
                    if (cabinetMetier.deleteMedecin(idMedecinSupprimer)) {
                        System.out.println("Médecin supprimé avec succès !");
                    } else {
                        System.out.println("Erreur lors de la suppression !");
                    }
                    break;
                case 11:
                    System.out.print("ID du patient pour rechercher ces consultation : ");
                    int idPatientConsultations = scanner.nextInt();
                    List<Consultation> consultationsPatient =cabinetMetier.getConsultationsByPatient(idPatientConsultations);
                    if (!consultationsPatient.isEmpty()) {
                        consultationsPatient.forEach(System.out::println);
                    } else {
                        System.out.println("Aucune consultation trouve !");
                    }
                    break;

                case 12:
                    System.out.print("ID du medecin pour rechercher ces consultation : ");
                    int idMedecinConsultations = scanner.nextInt();
                    List<Consultation> consultationsMedecin =cabinetMetier.getConsultationsByMedecin(idMedecinConsultations);
                    if (!consultationsMedecin.isEmpty()) {
                        consultationsMedecin.forEach(System.out::println);
                    } else {
                        System.out.println("Aucune consultation trouve !");
                    }
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }
        } while (choix != 0);

        scanner.close();
    }
}
