package com.pedidosms.pedidos.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pedidosms.pedidos.dto.PedidosDTO;
import com.pedidosms.pedidos.entity.Pedidos;

@Component
public class PedidosMapper {

	public PedidosDTO convertEntityToDto(Pedidos dado) {
		PedidosDTO dto = new PedidosDTO();

		if (dado != null) {
			dto.setId(dado.getId());
			dto.setSituacaoPedido(dado.getSituacaoPedido());
			dto.setClienteId(dado.getClienteId());
		}
		return dto;
	}

	public Pedidos convertDtoToEntity(PedidosDTO dto) {
		Pedidos entity = new Pedidos();

		if (dto != null) {
			entity.setId(dto.getId());
			entity.setSituacaoPedido(dto.getSituacaoPedido());
			entity.setClienteId(dto.getClienteId());
		}
		return entity;
	}

	public List<PedidosDTO> convertListEntityToListDto(List<Pedidos> listaDado) {
		List<PedidosDTO> listDto = new ArrayList<>();

		if (listDto != null) {
			for (Pedidos dado : listaDado) {
				listDto.add(convertEntityToDto(dado));
			}
		}
		return listDto;

	}

	public List<Pedidos> convertListDtoToListEntity(List<PedidosDTO> listaDto) {
		List<Pedidos> listDado = new ArrayList<>();

		if (listaDto != null) {
			for (PedidosDTO dado : listaDto) {
				listDado.add(convertDtoToEntity(dado));
			}
		}

		return listDado;
	}
	
}
