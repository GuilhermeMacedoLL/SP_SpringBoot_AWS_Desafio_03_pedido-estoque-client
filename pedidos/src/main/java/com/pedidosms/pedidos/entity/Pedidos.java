package com.pedidosms.pedidos.entity;

import com.pedidosms.pedidos.enums.SituacaoPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "PEDIDOS")
@Getter
@Setter
public class Pedidos {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "ID_PEDIDO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pedidos_seq")
	@SequenceGenerator(name="id_pedido", sequenceName="pedidos_seq", allocationSize=1)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "SITUACAO_PEDIDO")
	private SituacaoPedido situacaoPedido;

	@Column(name = "ID_CLIENTES")
	private Long clienteId;
	
	@Column(name = "PRODUTO")
	private String nomeProduto;
 
	@Column(name = "QUANTIDADE_PRODUTO")
	private int quantidadeProduto;

}
