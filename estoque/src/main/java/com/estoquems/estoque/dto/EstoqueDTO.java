package com.estoquems.estoque.dto;

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
	private String quantidade;
	private String nomeProduto;
}
