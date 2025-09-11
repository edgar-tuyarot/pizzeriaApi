package org.example.dao;

import org.example.model.Pedido;
import org.example.db.Database;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class PedidoDAO {
    public Pedido guardar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (cliente_id) VALUES (?)";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pedido.getClienteId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setId(rs.getInt(1));
                    pedido.setFecha(new Timestamp(System.currentTimeMillis()));
                    return pedido;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar pedido: " + e.getMessage());
        }

        return null;
    }
}


