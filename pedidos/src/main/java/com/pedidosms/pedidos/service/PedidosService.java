package com.pedidosms.pedidos.service;

import java.util.List;

import com.pedidosms.pedidos.dto.PedidosDTO;
import com.pedidosms.pedidos.entity.Pedidos;

public interface PedidosService {
	
	List<Pedidos> getAll();
	
	Pedidos getPedidos(Long id);
	
	Pedidos create(PedidosDTO dtoInput);
	
	Pedidos update(PedidosDTO dtoInput);
	
	void delete(Long id);

}
