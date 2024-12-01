package com.pedidosms.pedidos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pedidosms.pedidos.dto.PedidosDTO;
import com.pedidosms.pedidos.util.MediaType;

@Component
@FeignClient(name = "cliente", url = "localhost:8080", path = "/clientes")
public interface PedidosClient {
	
	@GetMapping(value = "/consulta/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<ClientesDTO> getNome(@PathVariable Long id);

}
