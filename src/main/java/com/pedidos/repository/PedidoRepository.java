package com.pedidos.repository;

import com.pedidos.model.Pedido;
import com.pedidos.model.Pedido.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByClienteIdentificacion(int clienteId);

    List<Pedido> findByEstado(EstadoPedido estado);

}