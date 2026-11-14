package io.github.ms_icompras.pedidos.validator;

import feign.FeignException;
import io.github.ms_icompras.pedidos.client.ClientesClient;
import io.github.ms_icompras.pedidos.client.ProdutosClient;
import io.github.ms_icompras.pedidos.client.representation.ClienteRepresentation;
import io.github.ms_icompras.pedidos.client.representation.ProdutoRepresentation;
import io.github.ms_icompras.pedidos.model.ItemPedido;
import io.github.ms_icompras.pedidos.model.Pedido;
import io.github.ms_icompras.pedidos.model.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoValidator {
    private final ProdutosClient produtosClient;
    private final ClientesClient clientesClient;

    public void validar(Pedido pedido){
//       List<Long> codigosProdutos =
//               pedido.getItens()
//                       .stream()
//                       .map(i  -> i.getCodigoProduto()).toList();
//       codigosProdutos.forEach( codigoProduto -> {
//           ResponseEntity<ProdutoRepresentation> response = produtosClient.buscarPorCodigo(codigoProduto);
//           ProdutoRepresentation produto = response.getBody();
//       });

        Long codigoCliente = pedido.getCodigoCliente();
        validarCliente(codigoCliente);
        pedido.getItens().forEach(this::validarItem);
    }

    private void validarCliente(Long codigoCliente){
        try {
            var response = clientesClient.buscarPorCodigo(codigoCliente);
            ClienteRepresentation cliente = response.getBody();
            log.info("Cliente de codigo {} encontrado: {}", cliente.codigo(), cliente.nome());
        }catch (FeignException.NotFound e){
            var message = String.format("Cliente de codigo %d não encontrado", codigoCliente);
            throw new ValidationException("codigoCliente",message);
        }
    }

    private void validarItem(ItemPedido item){
        try {
            var response = produtosClient.buscarPorCodigo(item.getCodigoProduto());
            ProdutoRepresentation produto = response.getBody();
            log.info("Produto de codigo {} encontrado: {}", produto.codigo(), produto.nome());
        }catch (FeignException.NotFound e){
            var message = String.format("Produto de codigo %d não encontrado", item.getCodigo());
            throw new ValidationException("codigoProduto",message);
        }
    }
}
