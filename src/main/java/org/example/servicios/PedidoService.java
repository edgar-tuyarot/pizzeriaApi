package org.example.servicios;


import org.example.dao.*;
import org.example.model.*;

import java.util.List;

public class PedidoService {
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final PedidoPizzaDAO pedidoPizzaDAO = new PedidoPizzaDAO();

    public Pedido registrarPedido(Cliente cliente, List<PedidoPizza> pizzas) {
        Cliente clienteGuardado = clienteDAO.guardar(cliente);
        Pedido pedido = pedidoDAO.guardar(new Pedido(0, null, clienteGuardado.getId()));

        for (PedidoPizza pp : pizzas) {
            pp.setPedido_id(pedido.getId());
            pedidoPizzaDAO.guardar(pp);
        }

        return pedido;
    }
}
