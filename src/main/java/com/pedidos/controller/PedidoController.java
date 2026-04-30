package com.pedidos.controller;

import com.pedidos.model.Pedido;
import com.pedidos.model.Pedido.EstadoPedido;
import com.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable int id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> buscarPorCliente(@PathVariable int clienteId) {
        return pedidoService.buscarPorCliente(clienteId);
    }

    @GetMapping("/estado/{estado}")
    public List<Pedido> buscarPorEstado(@PathVariable EstadoPedido estado) {
        return pedidoService.buscarPorEstado(estado);
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.guardar(pedido));
    }

    @PostMapping("/{pedidoId}/productos/{productoId}")
    public ResponseEntity<Pedido> agregarProducto(
            @PathVariable int pedidoId,
            @PathVariable int productoId,
            @RequestParam int cantidad) {
        try {
            return ResponseEntity.ok(pedidoService.agregarProductoAPedido(pedidoId, productoId, cantidad));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{pedidoId}/detalles/{detalleId}")
    public ResponseEntity<Pedido> eliminarProducto(
            @PathVariable int pedidoId,
            @PathVariable int detalleId) {
        try {
            return ResponseEntity.ok(pedidoService.eliminarProductoDePedido(pedidoId, detalleId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(
            @PathVariable int id,
            @RequestParam EstadoPedido estado) {
        try {
            return ResponseEntity.ok(pedidoService.actualizarEstado(id, estado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable int id) {
        pedidoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}