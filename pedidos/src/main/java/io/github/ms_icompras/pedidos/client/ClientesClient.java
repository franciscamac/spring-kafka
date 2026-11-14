package io.github.ms_icompras.pedidos.client;

import io.github.ms_icompras.pedidos.client.representation.ClienteRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientes", url = "${icompras.pedidos.clients.clientes.url}")
public interface ClientesClient {

    @GetMapping("{codigo}")
    ResponseEntity<ClienteRepresentation> buscarPorCodigo(@PathVariable("codigo") Long codigo);
}
