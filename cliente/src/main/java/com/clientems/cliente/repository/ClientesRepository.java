package com.clientems.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clientems.cliente.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long>, JpaSpecificationExecutor<Clientes>{

	@Query(value = "SELECT * FROM CLIENTES WHERE NOME=:nome", nativeQuery = true)
	Clientes getClientes(@Param("nome") String nome);
}
