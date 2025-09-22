package org.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private int cliente_id;
}

