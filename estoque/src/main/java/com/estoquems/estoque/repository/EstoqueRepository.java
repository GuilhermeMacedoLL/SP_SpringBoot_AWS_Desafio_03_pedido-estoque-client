package com.estoquems.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.estoquems.estoque.entity.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>, JpaSpecificationExecutor<Estoque> {

	@Query(value = "SELECT * FROM ESTOQUE WHERE NOME_PRODUTO=:nomeProduto", nativeQuery = true)
	List<Estoque> getListaEstoque(@Param("nomeProduto") String nomeProduto);
	
	@Query(value = "SELECT * FROM ESTOQUE WHERE NOME_PRODUTO=:nomeProduto", nativeQuery = true)
	Estoque getEstoque(@Param("nomeProduto") String nomeProduto);

}
