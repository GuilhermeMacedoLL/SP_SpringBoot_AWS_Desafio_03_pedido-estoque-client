package com.clientems.cliente.service;

import java.util.List;

import com.clientems.cliente.dto.ClientesDTO;
import com.clientems.cliente.entity.Clientes;

public interface ClientesService {
	
	List<Clientes> getAll();
	
	Clientes getClientes(Long id);
	
	Clientes create(ClientesDTO dtoInput);
	
	Clientes update(ClientesDTO dtoInput);
	
	void delete(Long id);
	
	Long getIdByCpf(String cpf);

}
