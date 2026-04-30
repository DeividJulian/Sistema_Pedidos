package com.pedidos.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "identificacion")
public class Cliente extends Persona {

    private String correo;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {}

    public Cliente(String nombre, int identificacion, String direccion, int telefono, String correo) {
        super(nombre, identificacion, direccion, telefono);
        this.correo = correo;
    }

    public void realizarPedido(Pedido pedido) {
        pedido.setCliente(this);
        this.pedidos.add(pedido);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}