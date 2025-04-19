package org.example.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EtudiantDAO {
    public static void afficherEtudiants() {
        String query = "SELECT * FROM etudiant";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery() )
        {
            while (rs.next()) {
                System.out.println("ID:" + rs.getInt("id") +
                        ", Nom:" + rs.getString("nom") +
                        ", Prénom:" + rs.getString("prenom") +
                        ", Age:" + rs.getInt("age"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public static void ajouterEtudiant(String nom, String prenom, int age) {
        String query = "INSERT INTO etudiant (nom, prenom, age) VALUES (?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom); stmt.setString(2, prenom); stmt.setInt(3, age);
            stmt.executeUpdate();
            System.out.println("Étudiant ajouté avec succès !");
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public static void supprimerEtudiant(int id) {
        String query = "DELETE FROM etudiant WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) System.out.println("Étudiant supprimé avec succès !");
            else System.out.println("Aucun étudiant trouvé avec cet ID.");
        } catch (SQLException e) { e.printStackTrace(); }
    }
}