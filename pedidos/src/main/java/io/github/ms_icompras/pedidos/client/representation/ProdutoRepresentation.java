package io.github.ms_icompras.pedidos.client.representation;

import java.math.BigDecimal;

public record ProdutoRepresentation(Long codigo, String nome, BigDecimal valorUnitario ) {

}
