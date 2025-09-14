package org.example.controller;


import org.example.dao.PedidoPizzaDAO;
import org.example.model.Pedido;
import org.example.model.PedidoPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/pedidos-pizzas")
@RestController
public class PedidoPizzaController {


    private final PedidoPizzaDAO pedidoPizzaDAO;

    @Autowired
    public PedidoPizzaController(PedidoPizzaDAO pedidoPizzaDAO) {
        this.pedidoPizzaDAO = pedidoPizzaDAO;
    }

    // GET /api/pedidos-pizzas
    @GetMapping
    public List<PedidoPizza> listar() {
        return pedidoPizzaDAO.listar();
    }

    // POST /api/pedidos-pizzas
    @PostMapping
    public PedidoPizza guardar(@RequestBody PedidoPizza pedidoPizza) {
        return pedidoPizzaDAO.guardar(pedidoPizza);
    }
    @PutMapping("/{id}")
    public PedidoPizza actualizar(@PathVariable int id, @RequestBody PedidoPizza pedidoPizza) {
        pedidoPizza.setId(id);
        return pedidoPizzaDAO.actualizar(pedidoPizza);
    }

    // GET /api/pedidos-pizzas/{id}
    @GetMapping("/{id}")
    public PedidoPizza buscarPorId(@PathVariable int id) {
        return pedidoPizzaDAO.buscarPorId(id);
    }

    // DELETE /api/pedidos-pizzas/{id}
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        pedidoPizzaDAO.eliminar(id);
    }

}
