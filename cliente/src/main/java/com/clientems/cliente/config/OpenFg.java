package com.clientems.cliente.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.clientems.cliente.dto.ClientesDTO;

@FeignClient(value = "OpenFg-Cliente", url = "http://localhost:8082/pedido")
public interface OpenFg {

	@PostMapping(value = "/consulta")
	public ClientesDTO clientePedidoRetorno(ClientesDTO dto); 
	
}
