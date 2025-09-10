package org.example;

import org.example.dao.PizzaDAO;
import org.example.db.Database;
import org.example.model.Pizza;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = Database.connect()) {
            System.out.println("Conexión exitosa a PostgreSQL");

            PizzaDAO dao = new PizzaDAO();
            Pizza nueva = new Pizza("Muzzarella", "Queso, tomate", 1300);
            Pizza guardada = dao.guardar(nueva);

            if (guardada != null) {
                System.out.println("Pizza guardada con ID: " + guardada.getId());
            } else {
                System.out.println("No se pudo guardar la pizza");
            }




        } catch (Exception e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}

