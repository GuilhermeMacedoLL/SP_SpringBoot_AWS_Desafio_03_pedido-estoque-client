package com.clientems.cliente.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.clientems.cliente.dto.ClientesDTO;
import com.clientems.cliente.dto.ClientesPedidoDTO;
import com.clientems.cliente.dto.PedidosDTO;
import com.clientems.cliente.mapper.ClientesMapper;
import com.clientems.cliente.service.ClientesService;
import com.clientems.cliente.util.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/clientes")
public class ClientesController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ClientesService service;

	@Autowired
	private ClientesMapper mapper;

	@GetMapping(value = "/consulta", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<ClientesDTO>> getAll() {
		List<ClientesDTO> listaDto = new ArrayList<>();
		listaDto = this.mapper.convertListEntityToListDto(this.service.getAll());
		return new ResponseEntity<List<ClientesDTO>>(listaDto, HttpStatus.OK);
	}

	@GetMapping(value = "/consulta/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<ClientesDTO> getNome(@PathVariable Long id) {
		ClientesDTO dto = new ClientesDTO();
		dto = this.mapper.convertEntityToDto(this.service.getClientes(id));
		return new ResponseEntity<ClientesDTO>(dto, HttpStatus.OK);
	}

	@GetMapping(value = "/pedidosByCpf/{cpf}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<PedidosDTO[]> getHistoricoPedido(@PathVariable String cpf) {

		Long clienteId = service.getIdByCpf(cpf);

		if (clienteId != null) {
			String url = "http://localhost:8082/pedidos/consulta/clienteId";

			try {
				HttpEntity<ClientesPedidoDTO> requestEntity = new HttpEntity<ClientesPedidoDTO>(new ClientesPedidoDTO(clienteId));
				ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
				if (response.getBody() != null) {
					ObjectMapper objectMapper = new ObjectMapper();
					PedidosDTO[] listPedidos = objectMapper.readValue(response.getBody(), PedidosDTO[].class);

					return new ResponseEntity<PedidosDTO[]>(listPedidos, HttpStatus.OK);
				}
				return null;
			} catch (Exception e) {
				log.error("Erro ao chamar o serviço de pedidos: ", e);
				return null;
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/pedidos/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> getPedidosByClienteId(@PathVariable Long id) {
		String url = "http://localhost:8082/pedidos/consulta/clienteId/" + id;

		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
			return response;
		} catch (Exception e) {
			log.error("Erro ao chamar o serviço de pedidos: ", e);
			return new ResponseEntity<>("Erro ao chamar o serviço de pedidos", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/salvar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<ClientesDTO> create(@RequestBody ClientesDTO dtoInput) {
		ClientesDTO dto = new ClientesDTO();
		dto = this.mapper.convertEntityToDto(this.service.create(dtoInput));
		return new ResponseEntity<ClientesDTO>(dto, HttpStatus.CREATED);
	}

	@PutMapping(value = "/alterar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<ClientesDTO> update(@RequestBody ClientesDTO dtoInput) {
		ClientesDTO dto = new ClientesDTO();
		dto = this.mapper.convertEntityToDto(this.service.update(dtoInput));
		return new ResponseEntity<ClientesDTO>(dto, HttpStatus.NO_CONTENT);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/excluir/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
