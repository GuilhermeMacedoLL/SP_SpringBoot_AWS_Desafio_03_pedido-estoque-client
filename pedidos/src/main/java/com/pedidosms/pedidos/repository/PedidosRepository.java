package com.pedidosms.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pedidosms.pedidos.entity.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long>, JpaSpecificationExecutor<Pedidos>{

	@Query(value = "SELECT * FROM PEDIDOS WHERE ID_PEDIDO=:id", nativeQuery = true)
	Pedidos getPedidos(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM PEDIDOS WHERE ID_CLIENTES = :clienteId", nativeQuery = true)
	List<Pedidos> getPedidosByClienteId(@Param("clienteId") Long clienteId);
	
}
