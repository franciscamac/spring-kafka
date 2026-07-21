package io.github.ms_icompras.pedidos.controller.dto.mappers;

import io.github.ms_icompras.pedidos.controller.dto.ItemPedidoDTO;
import io.github.ms_icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.ms_icompras.pedidos.model.ItemPedido;
import io.github.ms_icompras.pedidos.model.Pedido;
import io.github.ms_icompras.pedidos.model.enums.StatusPedido;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    ItemPedidoMapper ITEM_PEDIDO_MAPPER = Mappers.getMapper(ItemPedidoMapper.class);

    @Mapping(source = "itens", target = "itens", qualifiedByName = "mapItens")
    @Mapping(source = "dadosPagamento", target = "dadosPagamento")
    Pedido map(NovoPedidoDTO dto);

    @Named("mapItens")
    default List<ItemPedido> mapItens(List<ItemPedidoDTO> dtos) {
        return dtos.stream().map(ITEM_PEDIDO_MAPPER::map).toList();
    }

    @AfterMapping
    default void afterMapping(@MappingTarget Pedido pedido) {
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setDataPedido(java.time.LocalDateTime.now());

        var total = calcularTotal(pedido);
        pedido.setTotal(total);
    }
        private static BigDecimal calcularTotal(Pedido pedido) {
            return pedido.getItens().stream().map(item ->
                    item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()))
            ).reduce(BigDecimal.ZERO, BigDecimal::add).abs();
    }
}
