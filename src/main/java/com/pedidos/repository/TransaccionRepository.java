package com.pedidos.repository;

import com.pedidos.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {
    List<Transaccion> findByPedidoId(int pedidoId);
    List<Transaccion> findByEstado(String estado);
}