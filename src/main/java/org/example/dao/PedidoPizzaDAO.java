package org.example.dao;

import org.example.model.Pedido;
import org.example.model.PedidoPizza;
import org.example.db.Database;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PedidoPizzaDAO {

    public PedidoPizza guardar(PedidoPizza pp) {
        String sql = "INSERT INTO et_pedidos_pizzas (pedido_id, pizza_id, cantidad) VALUES (?, ?, ?)";
        System.out.println(pp);
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pp.getPedido_id());
            stmt.setInt(2, pp.getPizza_id());
            stmt.setInt(3, pp.getCantidad());
            stmt.executeUpdate();

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);

                        return new PedidoPizza(idGenerado, pp.getPedido_id(), pp.getPizza_id(),pp.getCantidad());
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar pedido_pizza: " + e.getMessage());

        }

        return null;
    }

    public List<PedidoPizza> listar(){
        List<PedidoPizza> pedidosPizzas = new ArrayList<>();
        String sql = "SELECT * FROM et_pedidos_pizzas";

        try  (Connection conn = Database.connect();
              Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()) {
                    PedidoPizza pedidosPizza = new PedidoPizza(
                            rs.getInt("id"),
                            rs.getInt("pizza_id"),
                            rs.getInt("pedido_id"),
                            rs.getInt("cantidad")
                    );
                    pedidosPizzas.add(pedidosPizza);
                }



        }catch (SQLException e) {
            System.out.println("Error  al guardar pedido_pizza: " + e.getMessage());

        }
        return pedidosPizzas;
    }

    public PedidoPizza actualizar(PedidoPizza pedidoPizza){
        String sql = "UPDATE et_pedido_pizzas SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedidoPizza.getId());
            stmt.setInt(2, pedidoPizza.getPedido_id());
            stmt.setInt(3, pedidoPizza.getPizza_id());
            stmt.setInt(4, pedidoPizza.getCantidad());
            stmt.executeUpdate();
            return pedidoPizza;
        } catch (SQLException e) {
            System.out.println("Error  al actualizar pedido: " + e.getMessage());
            return null;
        }


    }

    public PedidoPizza buscarPorId (int id) {
        String sql = "SELECT * FROM et_pedido_pizzas WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PedidoPizza pedidoPizza = new PedidoPizza();
                    pedidoPizza.setId(rs.getInt("id"));
                    pedidoPizza.setPedido_id(rs.getInt("pedido_id"));
                    pedidoPizza.setPizza_id(rs.getInt("cliente_id"));
                    pedidoPizza.setCantidad(rs.getInt("cantidad"));
                    return pedidoPizza;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar pedido por ID: " + e.getMessage());

        }

        return null;
    }

    public boolean eliminar(int id){

        String sql = "DELETE FROM et_pedidos_pizzas WHERE id = ?";

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

