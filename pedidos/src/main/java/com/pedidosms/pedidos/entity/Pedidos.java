package com.pedidosms.pedidos.entity;

import com.pedidosms.pedidos.enums.SituacaoPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Builder
@Table(name = "PEDIDOS")
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class Pedidos {
	
	@Id
	@Column(name = "PEDIDOS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "SITUACAO_PEDIDO")
	private SituacaoPedido situacaoPedido;
	
	@Id
	@Column(name = "CLIENTES_ID")
	@GeneratedValue(strategy = GenerationType.UUID)
	private Long clienteId;

}
