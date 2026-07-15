package io.github.ms_icompras.pedidos.controller.dto.mappers;

import io.github.ms_icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.ms_icompras.pedidos.model.Pedido;

public interface PedidoMapper {

    Pedido map(NovoPedidoDTO dto);
}
