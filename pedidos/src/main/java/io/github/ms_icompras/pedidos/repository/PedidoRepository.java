package io.github.ms_icompras.pedidos.repository;

import io.github.ms_icompras.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
