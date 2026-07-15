package io.github.ms_icompras.pedidos.service;

import io.github.ms_icompras.pedidos.repository.ItemPedidoRepository;
import io.github.ms_icompras.pedidos.repository.PedidoRepository;
import io.github.ms_icompras.pedidos.validator.PedidoValidator;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoValidator pedidoValidator;


}
