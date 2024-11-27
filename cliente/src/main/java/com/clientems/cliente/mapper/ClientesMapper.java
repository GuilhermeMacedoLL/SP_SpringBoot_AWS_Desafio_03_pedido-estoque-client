package com.clientems.cliente.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.clientems.cliente.dto.ClientesDTO;
import com.clientems.cliente.entity.Clientes;

@Service
public class ClientesMapper {

	public ClientesDTO convertEntityToDto(Clientes dado) {
		ClientesDTO dto = new ClientesDTO();

		if (dado != null) {
			dto.setId(dado.getId());
			dto.setNome(dado.getNome());
			dto.setSobreNome(dado.getSobreNome());
			dto.setCpf(dado.getCpf());
		}
		return dto;
	}

	public Clientes convertDtoToEntity(ClientesDTO dto) {
		Clientes entity = new Clientes();

		if (dto != null) {
			entity.setId(dto.getId());
			entity.setNome(dto.getNome());
			entity.setSobreNome(dto.getSobreNome());
			entity.setCpf(dto.getCpf());
		}
		return entity;
	}

	public List<ClientesDTO> convertListEntityToListDto(List<Clientes> listaDado) {
		List<ClientesDTO> listDto = new ArrayList<>();

		if (listDto != null) {
			for (Clientes dado : listaDado) {
				listDto.add(convertEntityToDto(dado));
			}
		}
		return listDto;

	}

	public List<Clientes> convertListDtoToListEntity(List<ClientesDTO> listaDto) {
		List<Clientes> listDado = new ArrayList<>();

		if (listaDto != null) {
			for (ClientesDTO dado : listaDto) {
				listDado.add(convertDtoToEntity(dado));
			}
		}

		return listDado;
	}

}
