package org.example.dao;


import org.example.model.Pedido;
import org.example.db.Database;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoDAO {

    public List<Pedido> listar() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY id ASC";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getTimestamp("fecha"),
                        rs.getInt("cliente_id")
                );
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return pedidos;
    }


    public Pedido guardar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (fecha, cliente_id) VALUES (?,?)";

        try (Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, pedido.getFecha());
            stmt.setInt(2, pedido.getCliente_id());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        return new Pedido(idGenerado, pedido.getFecha(), pedido.getCliente_id());
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar pedido: " + e.getMessage());
        }

        return null;
    }

    public Pedido actualizar(Pedido pedido){
        String sql = "UPDATE pedidos SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getId());
            stmt.setTimestamp(2, pedido.getFecha());
            stmt.setInt(3, pedido.getCliente_id());
            stmt.setInt(4, pedido.getId());
            stmt.executeUpdate();
            return pedido;
        } catch (SQLException e) {
            System.out.println("Error al actualizar pedido: " + e.getMessage());
            return null;
        }


    }

    public Pedido buscarPorId (int id) {
        String sql = "SELECT * FROM pedidos WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setId(rs.getInt("id"));
                    pedido.setFecha(rs.getTimestamp("fecha"));
                    pedido.setCliente_id(rs.getInt("clienteId"));
                    return pedido;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar pedido por ID: " + e.getMessage());

        }

        return null;
    }

    public boolean eliminar(int id){

        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }


    }


}


