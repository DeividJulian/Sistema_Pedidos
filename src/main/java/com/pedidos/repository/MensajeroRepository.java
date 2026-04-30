package com.pedidos.repository;

import com.pedidos.model.Mensajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MensajeroRepository extends JpaRepository<Mensajero, Integer> {
    List<Mensajero> findByDisponibilidad(boolean disponibilidad);
}