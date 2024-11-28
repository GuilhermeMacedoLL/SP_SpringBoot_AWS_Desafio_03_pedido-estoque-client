package com.estoquems.estoque.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.estoquems.estoque.dto.EstoqueDTO;
import com.estoquems.estoque.entity.Estoque;

@Component
public class EstoqueMapper {

	public EstoqueDTO convertEntityToDto(Estoque dado) {
		EstoqueDTO dto = new EstoqueDTO();
		
		if (dado != null) {
			dto.setId(dado.getId());
			dto.setQuantidade(dado.getQuantidade());
			dto.setNomeProduto(dado.getNomeProduto());
		}
		
		return dto;
	}
	
	public Estoque convertDtoToEntity(EstoqueDTO dto) {
		Estoque entity = new Estoque();
		
		if (dto != null) {
			entity.setId(dto.getId());
			entity.setQuantidade(dto.getQuantidade());
			entity.setNomeProduto(dto.getNomeProduto());
		}
		
		return entity;
	}
	
	public List<EstoqueDTO> convertListEntityToListDto(List<Estoque> listaDado){
		List<EstoqueDTO> listDto = new ArrayList<>();
		
		if (listDto != null) {
			for (Estoque dado : listaDado) {
				listDto.add(convertEntityToDto(dado));
			}
		}
		
		return listDto;
	}
	
	public List<Estoque> convertListDtoToListEntity(List<EstoqueDTO> listaDto) {
		List<Estoque> listDado = new ArrayList<>();

		if (listaDto != null) {
			for (EstoqueDTO dado : listaDto) {
				listDado.add(convertDtoToEntity(dado));
			}
		}

		return listDado;
	}
}
