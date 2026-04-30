package com.pedidos.service;

import com.pedidos.model.DetallePedido;
import com.pedidos.model.Pedido;
import com.pedidos.model.Producto;
import com.pedidos.model.Pedido.EstadoPedido;
import com.pedidos.repository.DetallePedidoRepository;
import com.pedidos.repository.PedidoRepository;
import com.pedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(int id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> buscarPorCliente(int clienteId) {
        return pedidoRepository.findByClienteIdentificacion(clienteId);
    }

    public List<Pedido> buscarPorEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public Pedido guardar(Pedido pedido) {
        pedido.crearPedido();
        return pedidoRepository.save(pedido);
    }

    public Pedido agregarProductoAPedido(int pedidoId, int productoId, int cantidad) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productoId));

        DetallePedido detalle = new DetallePedido(cantidad, producto.getPrecio(), 0, producto);
        detalle.calcularSubtotal();

        pedido.agregarProducto(detalle);

        return pedidoRepository.save(pedido);
    }

    public Pedido eliminarProductoDePedido(int pedidoId, int detalleId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));

        DetallePedido detalle = detallePedidoRepository.findById(detalleId)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con ID: " + detalleId));

        pedido.eliminarProducto(detalle);
        detallePedidoRepository.delete(detalle);

        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarEstado(int id, EstadoPedido nuevoEstado) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setEstado(nuevoEstado);
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    public void cancelar(int id) {
        pedidoRepository.findById(id).ifPresent(pedido -> {
            pedido.setEstado(EstadoPedido.CANCELADO);
            pedidoRepository.save(pedido);
        });
    }

    public void eliminar(int id) {
        pedidoRepository.deleteById(id);
    }
}