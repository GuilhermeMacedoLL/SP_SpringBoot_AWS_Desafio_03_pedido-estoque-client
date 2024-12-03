package com.pedidosms.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class EstoqueDTO {

	private Long id;
	private int quantidade;
	private String nomeProduto;
	
	
	public EstoqueDTO(String nomeProduto, int quantidade) {
		super();
		this.quantidade = quantidade;
		this.nomeProduto = nomeProduto;
	}
}
