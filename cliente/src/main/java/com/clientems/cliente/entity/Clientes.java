package com.clientems.cliente.entity;

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

@Data
@Entity
@Builder
@Table(name = "CLIENTES")
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class Clientes {
	
	@Id
	@Column(name = "ID_CLIENTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "SOBRENOME")
	private String sobreNome;
	
	@Column(name = "CPF")
	private String cpf;
	
	public Clientes(Long id2, Long clienteId, SituacaoPedido situacaoPedido) {
		// TODO Auto-generated constructor stub
	}

}
