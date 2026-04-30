package com.pedidos.service;

import com.pedidos.model.Envio;
import com.pedidos.model.EnvioEstandar;
import com.pedidos.model.EnvioExpress;
import com.pedidos.model.EnvioInternacional;
import com.pedidos.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> listarTodos() {
        return envioRepository.findAll();
    }

    public Optional<Envio> buscarPorId(int id) {
        return envioRepository.findById(id);
    }

    public List<Envio> buscarPorEstado(String estado) {
        return envioRepository.findByEstado(estado);
    }

    public EnvioEstandar crearEnvioEstandar(String observacion) {
        EnvioEstandar envio = new EnvioEstandar(0, "PENDIENTE", observacion);
        return (EnvioEstandar) envioRepository.save(envio);
    }

    public EnvioExpress crearEnvioExpress(String observacion) {
        EnvioExpress envio = new EnvioExpress(0, "PENDIENTE", observacion);
        return (EnvioExpress) envioRepository.save(envio);
    }

    public EnvioInternacional crearEnvioInternacional(String observacion, String paisDestino) {
        EnvioInternacional envio = new EnvioInternacional(0, "PENDIENTE", observacion, paisDestino);
        return (EnvioInternacional) envioRepository.save(envio);
    }

    public double calcularCostoEnvio(int envioId, double peso) {
        Envio envio = envioRepository.findById(envioId)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con ID: " + envioId));
        return envio.calcularCosto(peso);
    }

    public Envio actualizarEstado(int id, String nuevoEstado) {
        return envioRepository.findById(id).map(envio -> {
            envio.setEstado(nuevoEstado);
            return envioRepository.save(envio);
        }).orElseThrow(() -> new RuntimeException("Envío no encontrado con ID: " + id));
    }

    public void eliminar(int id) {
        envioRepository.deleteById(id);
    }
}