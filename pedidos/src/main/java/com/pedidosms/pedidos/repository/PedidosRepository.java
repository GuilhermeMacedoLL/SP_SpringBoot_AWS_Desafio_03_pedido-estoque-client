package com.pedidosms.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pedidosms.pedidos.entity.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long>, JpaSpecificationExecutor<Pedidos>{

	@Query(value = "SELECT * FROM PEDIDOS WHERE PEDIDOS_ID=:id", nativeQuery = true)
	Pedidos getPedidos(@Param("PEDIDOS_ID") Long id);
	
}
