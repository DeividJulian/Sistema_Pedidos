package com.pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "envio_estandar")
@PrimaryKeyJoinColumn(name = "id")
public class EnvioEstandar extends Envio {

    private static final double TARIFA_POR_KG = 2500.0;

    public EnvioEstandar() {}

    public EnvioEstandar(int id, String estado, String observacion) {
        super(id, estado, observacion);
    }

    @Override
    public double calcularCosto(double peso) {
        return peso * TARIFA_POR_KG;
    }
}