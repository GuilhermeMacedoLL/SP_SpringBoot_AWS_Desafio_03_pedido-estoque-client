package com.clientems.cliente.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clientems.cliente.util.MediaType;

@Component
@FeignClient(value = "estoque", url = "localhost:8081", path = "/estoque")
public interface ClienteClient {

	@GetMapping(value = "/consulta/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<EstoqueDTO> getNomeProduto(@PathVariable Long id); 
	
}
