package com.pedidos.service;

import com.pedidos.model.Envio;
import com.pedidos.model.Mensajero;
import com.pedidos.repository.EnvioRepository;
import com.pedidos.repository.MensajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MensajeroService {

    @Autowired
    private MensajeroRepository mensajeroRepository;

    @Autowired
    private EnvioRepository envioRepository;

    public List<Mensajero> listarTodos() {
        return mensajeroRepository.findAll();
    }

    public Optional<Mensajero> buscarPorId(int id) {
        return mensajeroRepository.findById(id);
    }

    public List<Mensajero> listarDisponibles() {
        return mensajeroRepository.findByDisponibilidad(true);
    }

    public Mensajero guardar(Mensajero mensajero) {
        return mensajeroRepository.save(mensajero);
    }

    public Mensajero actualizar(int id, Mensajero mensajeroActualizado) {
        return mensajeroRepository.findById(id).map(mensajero -> {
            mensajero.setNombre(mensajeroActualizado.getNombre());
            mensajero.setPlaca(mensajeroActualizado.getPlaca());
            mensajero.setTipoTransporte(mensajeroActualizado.getTipoTransporte());
            mensajero.setCapacidad(mensajeroActualizado.getCapacidad());
            mensajero.setDisponibilidad(mensajeroActualizado.getDisponibilidad());
            return mensajeroRepository.save(mensajero);
        }).orElseThrow(() -> new RuntimeException("Mensajero no encontrado con ID: " + id));
    }

    public Mensajero asignarEnvio(int mensajeroId, int envioId) {
        Mensajero mensajero = mensajeroRepository.findById(mensajeroId)
                .orElseThrow(() -> new RuntimeException("Mensajero no encontrado con ID: " + mensajeroId));
        Envio envio = envioRepository.findById(envioId)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con ID: " + envioId));

        mensajero.setEnvio(envio);
        mensajero.entregaPaquete();
        return mensajeroRepository.save(mensajero);
    }

    public Mensajero confirmarEntrega(int mensajeroId) {
        Mensajero mensajero = mensajeroRepository.findById(mensajeroId)
                .orElseThrow(() -> new RuntimeException("Mensajero no encontrado con ID: " + mensajeroId));
        mensajero.confirmarEntrega();
        return mensajeroRepository.save(mensajero);
    }

    public void eliminar(int id) {
        mensajeroRepository.deleteById(id);
    }
}