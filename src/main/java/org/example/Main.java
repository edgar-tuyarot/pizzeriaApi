package org.example;

import org.example.db.Database;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = Database.connect()) {
            System.out.println("Conexión exitosa a PostgreSQL");
        } catch (Exception e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}

