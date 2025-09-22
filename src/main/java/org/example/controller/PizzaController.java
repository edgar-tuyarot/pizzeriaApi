package org.example.controller;

import jakarta.validation.Valid;
import org.example.dao.PizzaDAO;
import org.example.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/pizzas")
@RestController
public class PizzaController {

    private final PizzaDAO pizzaDAO;

    @Autowired
    public PizzaController(PizzaDAO pizzaDAO) {
        this.pizzaDAO = pizzaDAO;
    }

    // GET /api/pizzas
    @GetMapping
    public List<Pizza> listar() {
        return pizzaDAO.listar();
    }

    // POST /api/pizzas
    @PostMapping
    public Pizza guardar(@Valid @RequestBody Pizza pizza) {

        return pizzaDAO.guardar(pizza);
    }
    @PutMapping("/{id}")
    public Pizza actualizar(@PathVariable int id,@Valid @RequestBody Pizza pizza) {
        pizza.setId(id);
        return pizzaDAO.actualizar(pizza);
    }

    // GET /api/pizzas/{id}
    @GetMapping("/{id}")
    public Pizza buscarPorId(@PathVariable int id) {
      return pizzaDAO.buscarPorId(id);
    }

    // DELETE /api/pizzas/{id}
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
       pizzaDAO.eliminar(id);
    }


}
