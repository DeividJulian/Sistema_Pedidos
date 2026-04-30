package com.pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "envio_internacional")
@PrimaryKeyJoinColumn(name = "id")
public class EnvioInternacional extends Envio {

    private String paisDestino;
    private static final double TARIFA_POR_KG = 15000.0;

    public EnvioInternacional() {}

    public EnvioInternacional(int id, String estado, String observacion, String paisDestino) {
        super(id, estado, observacion);
        this.paisDestino = paisDestino;
    }

    @Override
    public double calcularCosto(double peso) {
        return peso * TARIFA_POR_KG;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }
}