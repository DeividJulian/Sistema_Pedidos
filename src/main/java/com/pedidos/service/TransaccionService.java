package com.pedidos.service;

import com.pedidos.model.Pago;
import com.pedidos.model.Reembolso;
import com.pedidos.model.Transaccion;
import com.pedidos.repository.PagoRepository;
import com.pedidos.repository.ReembolsoRepository;
import com.pedidos.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ReembolsoRepository reembolsoRepository;

    public List<Transaccion> listarTodas() {
        return transaccionRepository.findAll();
    }

    public Optional<Transaccion> buscarPorId(int id) {
        return transaccionRepository.findById(id);
    }

    public List<Transaccion> buscarPorPedido(int pedidoId) {
        return transaccionRepository.findByPedidoId(pedidoId);
    }

    // --- PAGO ---
    public Pago procesarPago(Pago pago) {
        pago.procesarPago();
        return pagoRepository.save(pago);
    }

    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> buscarPagoPorId(int id) {
        return pagoRepository.findById(id);
    }

    // --- REEMBOLSO ---
    public Reembolso procesarReembolso(Reembolso reembolso) {
        reembolso.procesarReembolso();
        return reembolsoRepository.save(reembolso);
    }

    public List<Reembolso> listarReembolsos() {
        return reembolsoRepository.findAll();
    }

    public Optional<Reembolso> buscarReembolsoPorId(int id) {
        return reembolsoRepository.findById(id);
    }

    public void eliminar(int id) {
        transaccionRepository.deleteById(id);
    }
}