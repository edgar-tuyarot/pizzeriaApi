package org.example.controller;
import org.example.dao.PedidoDAO;
import org.example.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/pedidos")
@RestController
public class PedidoController {

    private final PedidoDAO pedidoDAO;

    @Autowired
    public PedidoController(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    // GET /api/pizzas
    @GetMapping
    public List<Pedido> listar() {
        return pedidoDAO.listar();
    }

    // POST /api/pizzas
    @PostMapping
    public Pedido guardar(@RequestBody Pedido pedido) {

        return pedidoDAO.guardar(pedido);
    }
    @PutMapping("/{id}")
    public Pedido actualizar(@PathVariable int id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return pedidoDAO.actualizar(pedido);
    }

    // GET /api/pizzas/{id}
    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable int id) {
        return pedidoDAO.buscarPorId(id);
    }

    // DELETE /api/pizzas/{id}
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        pedidoDAO.eliminar(id);
    }




}
