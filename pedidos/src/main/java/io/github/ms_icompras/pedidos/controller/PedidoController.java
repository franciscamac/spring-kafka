package io.github.ms_icompras.pedidos.controller;

import io.github.ms_icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.ms_icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    public ResponseEntity criar(@RequestBody NovoPedidoDTO novoPedidoDTO){
     return null;
    }
}
