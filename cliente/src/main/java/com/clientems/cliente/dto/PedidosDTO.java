package com.clientems.cliente.dto;

import com.clientems.cliente.enums.SituacaoPedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class PedidosDTO {
	
	private Long id;
	private SituacaoPedido situacaoPedido;
	private Long clienteId;
	private String nomeProduto;
	private int quantidadeProduto;

}
