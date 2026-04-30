package com.pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "envio_express")
@PrimaryKeyJoinColumn(name = "id")
public class EnvioExpress extends Envio {

    private static final double TARIFA_POR_KG = 5000.0;

    public EnvioExpress() {}

    public EnvioExpress(int id, String estado, String observacion) {
        super(id, estado, observacion);
    }

    @Override
    public double calcularCosto(double peso) {
        return peso * TARIFA_POR_KG;
    }
}