package com.pedidos.controller;

import com.pedidos.model.Mensajero;
import com.pedidos.service.MensajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mensajeros")
public class MensajeroController {

    @Autowired
    private MensajeroService mensajeroService;

    @GetMapping
    public List<Mensajero> listarTodos() {
        return mensajeroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensajero> buscarPorId(@PathVariable int id) {
        return mensajeroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibles")
    public List<Mensajero> listarDisponibles() {
        return mensajeroService.listarDisponibles();
    }

    @PostMapping
    public ResponseEntity<Mensajero> crear(@RequestBody Mensajero mensajero) {
        return ResponseEntity.ok(mensajeroService.guardar(mensajero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensajero> actualizar(@PathVariable int id, @RequestBody Mensajero mensajero) {
        try {
            return ResponseEntity.ok(mensajeroService.actualizar(id, mensajero));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{mensajeroId}/asignar-envio/{envioId}")
    public ResponseEntity<Mensajero> asignarEnvio(
            @PathVariable int mensajeroId,
            @PathVariable int envioId) {
        try {
            return ResponseEntity.ok(mensajeroService.asignarEnvio(mensajeroId, envioId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/confirmar-entrega")
    public ResponseEntity<Mensajero> confirmarEntrega(@PathVariable int id) {
        try {
            return ResponseEntity.ok(mensajeroService.confirmarEntrega(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        mensajeroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}