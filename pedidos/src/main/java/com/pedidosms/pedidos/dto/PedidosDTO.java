package com.pedidosms.pedidos.dto;

import com.pedidosms.pedidos.enums.SituacaoPedido;

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
	
	private long id;
	private SituacaoPedido situacaoPedido;
	private Long clienteId;

}
