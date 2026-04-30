package com.pedidos.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pago")
@PrimaryKeyJoinColumn(name = "id")
public class Pago extends Transaccion {

    private String metodo;

    public Pago() {}

    public Pago(int id, double monto, Date fecha, String estado, String metodo) {
        super(id, "PAGO", monto, fecha, estado);
        this.metodo = metodo;
    }

    public void procesarPago() {
        this.setEstado("PROCESADO");
        System.out.println("Pago procesado con método: " + metodo + ", monto: " + getMonto());
    }

    public void generarFactura() {
        System.out.println("Factura generada para pago ID: " + getId() + ", monto: " + getMonto());
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}