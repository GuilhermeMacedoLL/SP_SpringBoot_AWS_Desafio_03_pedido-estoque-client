package com.estoquems.estoque.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.estoquems.estoque.util.MediaType;

@Component
@FeignClient(value = "pedidos", url= "localhost:8081", path = "/pedidos")
public interface EstoqueClient {
	
	@GetMapping(value = "/consulta/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<PedidosDTO> getId(@PathVariable Long id);

}
