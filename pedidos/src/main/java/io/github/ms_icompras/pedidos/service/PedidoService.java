package io.github.ms_icompras.pedidos.service;

import io.github.ms_icompras.pedidos.client.ServicoBancarioClient;
import io.github.ms_icompras.pedidos.model.Pedido;
import io.github.ms_icompras.pedidos.repository.ItemPedidoRepository;
import io.github.ms_icompras.pedidos.repository.PedidoRepository;
import io.github.ms_icompras.pedidos.validator.PedidoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoValidator pedidoValidator;
    private final ServicoBancarioClient servicoBancarioClient;

    @Transactional
    public Pedido criarPedido(Pedido pedido){
        pedidoValidator.validar(pedido);
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItens());
        var chavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(chavePagamento);

        return pedido;
    }

}
