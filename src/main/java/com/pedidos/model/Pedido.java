package com.pedidos.model;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Enumerated(EnumType.STRING)
    private EstadoPedido  estado;
    private double total;
    private double pesoTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

   @JsonIgnore
@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<DetallePedido> detalles = new ArrayList<>();

@JsonIgnore
@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Transaccion> transacciones = new ArrayList<>();

    public Pedido() {}

    public Pedido(int id, Date fecha, EstadoPedido estado, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.total = 0;
        this.pesoTotal = 0;
    }

    public enum EstadoPedido {
        CREADO, PAGADO, CANCELADO
     }

 public void crearPedido(){
 this.estado= EstadoPedido.CREADO;
 System.out.println("Pedido " + id + " pedido ha sido creado.");
 }
 
 public void pagarPedido(){
 this.estado = EstadoPedido.PAGADO;
 System.out.println("Pedido" + id + "pedido ha sido pagado.");
 }

  public void cancelarPedido(){
 this.estado = EstadoPedido.CANCELADO;
 System.out.println("Pedido"+ id + "pedido ha sido cancelado");
 }

    public void agregarProducto(DetallePedido detalle) {
        detalle.calcularSubtotal();
        detalle.setPedido(this);
        this.detalles.add(detalle);
        calcularTotal();
    }

    public void eliminarProducto(DetallePedido detalle) {
        this.detalles.remove(detalle);
        calcularTotal();
    }

    public void calcularTotal() {
        this.total = 0;
        this.pesoTotal = 0;
        for (DetallePedido detalle : detalles) {
            this.total += detalle.getSubtotal();
            if (detalle.getProducto() != null) {
                this.pesoTotal += detalle.getProducto().getPeso() * detalle.getCantidad();
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
}

