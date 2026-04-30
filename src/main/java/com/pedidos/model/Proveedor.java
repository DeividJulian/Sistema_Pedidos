package com.pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedor")
@PrimaryKeyJoinColumn(name = "identificacion")
public class Proveedor extends Persona {

    public Proveedor() {}

    public Proveedor(String nombre, int identificacion, String direccion, int telefono) {
        super(nombre, identificacion, direccion, telefono);
    }

    public void despacharCompra() {
        System.out.println("Proveedor " + getNombre() + " ha despachado la compra.");
    }
}