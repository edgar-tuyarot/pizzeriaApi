package org.example.model;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@Getter @Setter
public class Pizza {
    private int id;
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;
    @Size(min = 20, message = "El texto debe tener al menos 20 caracteres")
    @NotBlank(message = "Ingredientes son obligatorios")
    private String ingredientes;
    @NotNull(message = "Indicar precio")
    @DecimalMin(value = "7499.99", message = "El precio debe ser mayor a 7499")
    private double precio;

    // Constructor para crear pizza sin ID (para guardar)
    public Pizza(String nombre, String ingredientes, double precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
    }

    // Constructor completo (para leer desde la base)
    public Pizza(int id, String nombre, String ingredientes, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
    }

}
