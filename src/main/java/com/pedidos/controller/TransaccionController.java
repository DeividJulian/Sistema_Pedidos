package com.pedidos.controller;

import com.pedidos.model.Pago;
import com.pedidos.model.Reembolso;
import com.pedidos.model.Transaccion;
import com.pedidos.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    public List<Transaccion> listarTodas() {
        return transaccionService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> buscarPorId(@PathVariable int id) {
        return transaccionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedido/{pedidoId}")
    public List<Transaccion> buscarPorPedido(@PathVariable int pedidoId) {
        return transaccionService.buscarPorPedido(pedidoId);
    }

    // --- PAGOS ---
    @GetMapping("/pagos")
    public List<Pago> listarPagos() {
        return transaccionService.listarPagos();
    }

    @GetMapping("/pagos/{id}")
    public ResponseEntity<Pago> buscarPagoPorId(@PathVariable int id) {
        return transaccionService.buscarPagoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/pagos")
    public ResponseEntity<Pago> procesarPago(@RequestBody Pago pago) {
        return ResponseEntity.ok(transaccionService.procesarPago(pago));
    }

    // --- REEMBOLSOS ---
    @GetMapping("/reembolsos")
    public List<Reembolso> listarReembolsos() {
        return transaccionService.listarReembolsos();
    }

    @GetMapping("/reembolsos/{id}")
    public ResponseEntity<Reembolso> buscarReembolsoPorId(@PathVariable int id) {
        return transaccionService.buscarReembolsoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/reembolsos")
    public ResponseEntity<Reembolso> procesarReembolso(@RequestBody Reembolso reembolso) {
        return ResponseEntity.ok(transaccionService.procesarReembolso(reembolso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        transaccionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}