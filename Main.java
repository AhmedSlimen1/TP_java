package org.example.demo1;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); int choix;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Afficher les étudiants");
            System.out.println("2. Ajouter un étudiant");
            System.out.println("3. Supprimer un étudiant");
            System.out.println("4. Quitter");
            System.out.print("Votre choix :");
            choix = scanner.nextInt(); scanner.nextLine();
            switch (choix) {
                case 1:
                    EtudiantDAO.afficherEtudiants(); break;
                case 2:
                    System.out.print("Nom :"); String nom = scanner.nextLine();
                    System.out.print("Prénom :"); String prenom = scanner.nextLine();
                    System.out.print("Âge :"); int age = scanner.nextInt();
                    scanner.nextLine();
                    EtudiantDAO.ajouterEtudiant(nom, prenom, age); break;
                case 3:
                    System.out.print("ID de l'étudiant à supprimer :");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    EtudiantDAO.supprimerEtudiant(id); break;
                case 4:
                    System.out.println("Fin du programme."); break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 4);
        scanner.close();
    }
}