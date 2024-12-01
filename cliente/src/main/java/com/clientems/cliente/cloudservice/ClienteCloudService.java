package com.clientems.cliente.cloudservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.clientems.cliente.entity.Clientes;
import com.pedidosms.pedidos.entity.Pedidos;

@Service
public class ClienteCloudService {

	@Value("${pedidos.host}")
	private String pedidosHost;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Clientes getPedido(Long id) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", ""+id);
		
		Pedidos pedido = restTemplate.getForObject(pedidosHost + "/pedidos/consulta/{id}", Pedidos.class, uriVariables);
		
		return new Clientes(pedido.getId(), pedido.getClienteId(), pedido.getSituacaoPedido());
	}
	
}
