package org.example.dao;

import org.example.db.Database;
import org.example.model.Pizza;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PizzaDAO {

    public List<Pizza> listar() {
        List<Pizza> pizzas = new ArrayList<>();
        String sql = "SELECT * FROM et_pizzas ORDER BY id ASC";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pizza pizza = new Pizza(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ingredientes"),
                        rs.getDouble("precio")
                );
                pizzas.add(pizza);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pizzas: " + e.getMessage());
        }

        return pizzas;
    }

    public Pizza guardar(Pizza pizza) {
        String sql = "INSERT INTO et_pizzas (nombre, ingredientes, precio) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, pizza.getNombre());
            stmt.setString(2, pizza.getIngredientes());
            stmt.setDouble(3, pizza.getPrecio());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        return new Pizza(idGenerado, pizza.getNombre(), pizza.getIngredientes(), pizza.getPrecio());
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar pizza: " + e.getMessage());
        }

        return null;
    }

    public Pizza actualizar(Pizza pizza){
        String sql = "UPDATE et_pizzas SET nombre = ?, ingredientes = ?, precio = ? WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pizza.getNombre());
            stmt.setString(2, pizza.getIngredientes());
            stmt.setDouble(3, pizza.getPrecio());
            stmt.setInt(4, pizza.getId());
            stmt.executeUpdate();
            return pizza;
        } catch (SQLException e) {
            System.out.println("Error al actualizar pizza: " + e.getMessage());
            return null;
        }


    }

    public Pizza buscarPorId (int id) {
        String sql = "SELECT * FROM pizzas WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Pizza pizza = new Pizza();
                    pizza.setId(rs.getInt("id"));
                    pizza.setNombre(rs.getString("nombre"));
                    pizza.setIngredientes(rs.getString("ingredientes"));
                    pizza.setPrecio(rs.getDouble("precio"));
                    return pizza;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar pizza por ID: " + e.getMessage());

        }

        return null;
        }

        public boolean eliminar(int id){

            String sql = "DELETE FROM pizzas WHERE id = ?";

            try (Connection conn = Database.connect();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                int filas = stmt.executeUpdate();
                return filas > 0;

            } catch (SQLException e) {
                System.out.println("Error al eliminar pizza: " + e.getMessage());
                return false;
            }


        }
    }


