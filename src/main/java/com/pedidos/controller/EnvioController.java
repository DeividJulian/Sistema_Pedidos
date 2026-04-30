package com.pedidos.controller;

import com.pedidos.model.Envio;
import com.pedidos.model.EnvioEstandar;
import com.pedidos.model.EnvioExpress;
import com.pedidos.model.EnvioInternacional;
import com.pedidos.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> listarTodos() {
        return envioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> buscarPorId(@PathVariable int id) {
        return envioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public List<Envio> buscarPorEstado(@PathVariable String estado) {
        return envioService.buscarPorEstado(estado);
    }

    @PostMapping("/estandar")
    public ResponseEntity<EnvioEstandar> crearEstandar(@RequestParam String observacion) {
        return ResponseEntity.ok(envioService.crearEnvioEstandar(observacion));
    }

    @PostMapping("/express")
    public ResponseEntity<EnvioExpress> crearExpress(@RequestParam String observacion) {
        return ResponseEntity.ok(envioService.crearEnvioExpress(observacion));
    }

    @PostMapping("/internacional")
    public ResponseEntity<EnvioInternacional> crearInternacional(
            @RequestParam String observacion,
            @RequestParam String paisDestino) {
        return ResponseEntity.ok(envioService.crearEnvioInternacional(observacion, paisDestino));
    }

    @GetMapping("/{id}/costo")
    public ResponseEntity<Double> calcularCosto(@PathVariable int id, @RequestParam double peso) {
        try {
            return ResponseEntity.ok(envioService.calcularCostoEnvio(id, peso));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Envio> actualizarEstado(@PathVariable int id, @RequestParam String estado) {
        try {
            return ResponseEntity.ok(envioService.actualizarEstado(id, estado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        envioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}