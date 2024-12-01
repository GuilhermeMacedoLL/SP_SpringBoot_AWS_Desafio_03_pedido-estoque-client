package com.estoquems.estoque.service;

import java.util.List;

import com.estoquems.estoque.dto.EstoqueDTO;
import com.estoquems.estoque.entity.Estoque;

public interface EstoqueService {
	
	List<Estoque> getAll();
	
	Estoque getEstoque(Long id);
	
	Estoque create(EstoqueDTO dtoInput);
	
	Estoque update(EstoqueDTO dtoInput);
	
	void delete(Long id);

}
