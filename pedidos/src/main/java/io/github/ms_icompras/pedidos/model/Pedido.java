package io.github.ms_icompras.pedidos.model;


import io.github.ms_icompras.pedidos.controller.dto.DadosPagamentoDTO;
import io.github.ms_icompras.pedidos.model.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/*
create database icompraspedidos;
create table pedido(
                       codigo serial not null primary key,
                       codigo_cliente bigint not null,
                       data_pedido timestamp not null default now(),
                       chave_pagamente text,
                       observacoes text,
                       status varchar(20) check (
                           status in ('REALIZADO', 'PAGO', 'FATURADO','ENVIADO', 'ERRO_PAGAMENTO', 'PREPARANDO_ENVIO')

                           ),
                       total decimal(16,2) not null,
                       codigo_rastreio varchar(255),
                       url_nf text

 */
@Entity
@Table(name ="pedido")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name="codigo_cliente", nullable = false)
    private Long codigoCliente;

    @Column(name="data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(name="total", precision = 16, scale = 2,nullable = false)
    private BigDecimal total;

    @Column(name = "chave_pagamente")
    private String chavePagamento;

    @Column(name="observacoes")
    private String observacoes;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Column(name="codigo_rastreio")
    private String codigoRastreio;

    @Column(name="url_nf")
    private String urlNotaFiscal;

    @Transient
    private DadosPagamento dadosPagamento;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

}
