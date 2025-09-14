package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoPizza {
    private int id;
    private int pedidoId;
    private int pizzaId;
    private int cantidad;
}

