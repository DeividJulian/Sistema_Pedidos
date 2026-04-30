package com.pedidos.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reembolso")
@PrimaryKeyJoinColumn(name = "id")
public class Reembolso extends Transaccion {

    private String motivo;

    public Reembolso() {}

    public Reembolso(int id, double monto, Date fecha, String estado, String motivo) {
        super(id, "REEMBOLSO", monto, fecha, estado);
        this.motivo = motivo;
    }

    public void procesarReembolso() {
        this.setEstado("REEMBOLSADO");
        System.out.println("Reembolso procesado. Motivo: " + motivo + ", monto: " + getMonto());
    }

    public void generarFactura() {
        System.out.println("Nota crédito generada para reembolso ID: " + getId());
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}