package org.example.dao;

import org.example.db.Database;
import org.example.model.Cliente;
import org.example.model.Pizza;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteDAO {

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM et_clientes ORDER BY id ASC";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return clientes;
    }


    public Cliente guardar(Cliente cliente) {
        String sql = "INSERT INTO et_clientes (nombre, telefono, direccion) VALUES (?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getDireccion());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        return new Cliente(idGenerado, cliente.getNombre(), cliente.getTelefono(), cliente.getDireccion());
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
        }

        return null;
    }

    public Cliente actualizar(Cliente cliente){
        String sql = "UPDATE et_clientes SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getDireccion());
            stmt.setInt(4, cliente.getId());
            stmt.executeUpdate();
            return cliente;
        } catch (SQLException e) {
            System.out.println("Error al actualizar Cliente: " + e.getMessage());
            return null;
        }


    }

    public Cliente buscarPorId (int id) {
        String sql = "SELECT * FROM et_clientes WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setDireccion(rs.getString("direccion"));
                    return cliente;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente por ID: " + e.getMessage());

        }

        return null;
    }

    public boolean eliminar(int id){

        String sql = "DELETE FROM et_clientes WHERE id = ?";

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

