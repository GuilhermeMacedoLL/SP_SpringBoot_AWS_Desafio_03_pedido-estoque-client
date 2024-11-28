package com.estoquems.estoque.entity;

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

@Data
@Entity
@Builder
@Table(name = "ESTOQUE")
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class Estoque {
	
	@Id
	@Column(name = "ID_ESTOQUE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "QUANTIDADE")
	private int quantidade;
	
	@Column(name = "NOME_PRODUTO")
	private String nomeProduto;

}
