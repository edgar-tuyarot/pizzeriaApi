package org.example.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private int id;
    private Timestamp fecha;
    @NotBlank
    private int cliente_id;
}

