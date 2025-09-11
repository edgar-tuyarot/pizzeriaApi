package org.example.dao;

import org.example.db.Database;
import org.example.model.Cliente;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class ClienteDAO {
    public Cliente guardar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, telefono, direccion) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getDireccion());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                    return cliente;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
        }

        return null;
    }
}

