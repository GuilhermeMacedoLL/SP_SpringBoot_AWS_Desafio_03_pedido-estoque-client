package com.clientems.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clientems.cliente.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long>, JpaSpecificationExecutor<Clientes>{

	@Query(value = "SELECT * FROM CLIENTES WHERE NOME=:nome", nativeQuery = true)
	Clientes getClientes(@Param("nome") String nome);
	
	@Query(value = "SELECT * FROM CLIENTES WHERE UPPER(NOME)=UPPER(:nome) AND UPPER(SOBRENOME)=UPPER(:sobreNome)", nativeQuery = true)
    Clientes getClientesByNomeESobrenome(@Param("nome") String nome, @Param("sobreNome") String sobreNome);

	@Query(value = "SELECT * FROM CLIENTES WHERE CPF=:cpf", nativeQuery = true)
	Clientes getClientesByCpf(@Param("cpf") String cpf);
	
	@Query(value = "INSERT INTO CLIENTES (id, cpf, nome, sobrenome) VALUES (:id, :cpf, :nome, :sobreNome)", nativeQuery = true)
	void insertCliente(@Param("id") Long id, @Param("cpf") String cpf, @Param("nome") String nome, @Param("sobreNome") String sobreNome);
}
