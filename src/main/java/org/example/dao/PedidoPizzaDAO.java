package org.example.dao;

import org.example.model.PedidoPizza;
import org.example.db.Database;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PedidoPizzaDAO {
    public boolean guardar(PedidoPizza pp) {
        String sql = "INSERT INTO pedido_pizzas (pedido_id, pizza_id, cantidad) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pp.getPedidoId());
            stmt.setInt(2, pp.getPizzaId());
            stmt.setInt(3, pp.getCantidad());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar pedido_pizza: " + e.getMessage());
            return false;
        }
    }
}

