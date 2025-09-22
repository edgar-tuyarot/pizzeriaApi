package org.example.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Indicar precio")
    @Pattern(regexp = "\\d+", message = "el precio debe ser un número válido")
    @Min(value = 6000)
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
