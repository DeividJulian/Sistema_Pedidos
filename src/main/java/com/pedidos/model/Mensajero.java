package com.pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mensajero")
@PrimaryKeyJoinColumn(name = "identificacion")
public class Mensajero extends Persona {

    private String placa;
    private String tipoTransporte;
    private double capacidad;
    private boolean disponibilidad;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "envio_id")
    private Envio envio;

    public Mensajero() {}

    public Mensajero(String nombre, int identificacion, String direccion, int telefono,
                     String placa, String tipoTransporte, double capacidad, boolean disponibilidad) {
        super(nombre, identificacion, direccion, telefono);
        this.placa = placa;
        this.tipoTransporte = tipoTransporte;
        this.capacidad = capacidad;
        this.disponibilidad = disponibilidad;
    }

    public void entregaPaquete() {
        if (this.disponibilidad) {
            System.out.println("Mensajero " + getNombre() + " está entregando el paquete.");
            this.disponibilidad = false;
        } else {
            System.out.println("Mensajero " + getNombre() + " no está disponible.");
        }
    }

    public void confirmarEntrega() {
        System.out.println("Entrega confirmada por mensajero: " + getNombre());
        if (this.envio != null) {
            this.envio.setEstado("ENTREGADO");
        }
        this.disponibilidad = true;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
}