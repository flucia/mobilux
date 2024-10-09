package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDb {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Mobilux";
        String user = "root";
        String password = "00000000";
        

        Connection conn = null;

        try {
            // Carica il driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Crea una connessione
            conn = DriverManager.getConnection(url, user,password);
            System.out.println("Connessione al database avvenuta con successo!");
        } catch (SQLException e) {
            System.err.println("Errore di connessione: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver non trovato: " + e.getMessage());
        } finally {
            // Chiudi la connessione se è stata aperta
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
