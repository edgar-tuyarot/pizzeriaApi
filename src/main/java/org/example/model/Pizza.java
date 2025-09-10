package org.example.model;
import lombok.*;


@Getter @Setter
public class Pizza {
    private int id;
    private String nombre;
    private String ingredientes;
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
