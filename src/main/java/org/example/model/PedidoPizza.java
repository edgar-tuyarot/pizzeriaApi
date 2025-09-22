package org.example.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoPizza {
    private int id;
    @NonNull @NotBlank
    private int pedidoId;
    @NonNull @NotBlank
    private int pizzaId;
    @Pattern(regexp = "\\d+", message = "La cantidad debe ser un número válido")
    @NotBlank(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

}

