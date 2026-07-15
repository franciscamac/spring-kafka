package io.github.ms_icompras.pedidos.controller.dto;

import io.github.ms_icompras.pedidos.model.enums.TipoPagamento;

public record DadosPagamentoDTO(String dados, TipoPagamento tipoPagamento) {
}
