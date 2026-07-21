package io.github.ms_icompras.pedidos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/*
create table  item_pedido(
                             codigo serial not null primary key,
                             codigo_pedido bigint not null references pedido (codigo),
                             codigo_produto bigint not null,
                             quantidade int not null,
                             valor_unitario decimal(16,2) not null
 */
@Entity
@Table(name ="item_pedido")
@Getter
@Setter
@NoArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long codigo;

    @JoinColumn(name="codigo_pedido", nullable = false)
    @ManyToOne
    private Pedido pedido;

    @Column(name="codigo_produto", nullable = false)
    private Long codigoProduto;

    @Column(name = "quantidade", nullable = false)
    private Long quantidade;

    @Column(name="valor_unitario", precision = 16, scale = 2, nullable = false)
    private BigDecimal valorUnitario;
}
