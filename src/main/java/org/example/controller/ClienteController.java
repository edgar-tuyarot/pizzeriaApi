package org.example.controller;

import jakarta.validation.Valid;
import org.example.dao.ClienteDAO;
import org.example.dao.PizzaDAO;
import org.example.model.Cliente;
import org.example.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/clientes")

@RestController
public class ClienteController {

    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    // GET /api/pizzas
    @GetMapping
    public List<Cliente> listar() {
        return clienteDAO.listar();
    }

    // POST /api/pizzas
    @PostMapping
    public Cliente guardar(@Valid @RequestBody Cliente cliente) {

        return clienteDAO.guardar(cliente);
    }
    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable int id, @Valid @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteDAO.actualizar(cliente);
    }

    // GET /api/pizzas/{id}
    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable int id) {
        return clienteDAO.buscarPorId(id);
    }

    // DELETE /api/pizzas/{id}
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        clienteDAO.eliminar(id);
    }




}
