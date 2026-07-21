package io.github.ms_icompras.pedidos.model;

import io.github.ms_icompras.pedidos.model.enums.TipoPagamento;
import lombok.Data;

@Data
public class DadosPagamento {
    private String dados;
    private TipoPagamento tipoPagamento;

}
