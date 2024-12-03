package com.pedidosms.pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedidosms.pedidos.dto.ClientesPedidoDTO;
import com.pedidosms.pedidos.dto.EstoqueDTO;
import com.pedidosms.pedidos.dto.PedidosDTO;
import com.pedidosms.pedidos.entity.Pedidos;
import com.pedidosms.pedidos.mapper.PedidosMapper;
import com.pedidosms.pedidos.service.PedidosService;
import com.pedidosms.pedidos.util.MediaType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/pedidos")
public class PedidosController {

	@Autowired
	private PedidosService service;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PedidosMapper mapper;

	@GetMapping(value = "/consulta", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<PedidosDTO>> getAll() {
		List<PedidosDTO> listaDto = this.mapper.convertListEntityToListDto(this.service.getAll());
		return new ResponseEntity<>(listaDto, HttpStatus.OK);
	}

	@GetMapping(value = "/consulta/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<PedidosDTO> getId(@PathVariable Long id) {
		Pedidos pedidos = this.service.getPedidos(id);
		if (pedidos == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		PedidosDTO dto = this.mapper.convertEntityToDto(pedidos);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping(value = "/consulta/clienteId", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<PedidosDTO>> getPedidosByClienteId(@RequestBody ClientesPedidoDTO dto) {

		List<PedidosDTO> pedidos = this.mapper.convertListEntityToListDto(service.getPedidosByClienteId(dto.getIdCliente()));

		return new ResponseEntity<List<PedidosDTO>>(pedidos, HttpStatus.OK);

	}
	
	@GetMapping(value = "/estoque/{nomeProduto}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<EstoqueDTO[]> getEstoque(@PathVariable String nomeProduto) {
		
		if (nomeProduto != null) {
			String url = "http://localhost:8081/estoque/consulta/produto/nomeProduto";

			try {
				ResponseEntity<String> response = restTemplate.exchange(url.replace("nomeProduto", nomeProduto),
						HttpMethod.GET, null, String.class);
				if (response.getBody() != null) {
					ObjectMapper objectMapper = new ObjectMapper();
					EstoqueDTO[] listEstoque = objectMapper.readValue(response.getBody(), EstoqueDTO[].class);

					return new ResponseEntity<EstoqueDTO[]>(listEstoque, HttpStatus.OK);
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

	@PostMapping(value = "/salvar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<PedidosDTO> create(@RequestBody PedidosDTO dtoInput) {
		PedidosDTO dto = this.mapper.convertEntityToDto(this.service.create(dtoInput));
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@PutMapping(value = "/alterar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<PedidosDTO> update(@RequestBody PedidosDTO dtoInput) {
		PedidosDTO dto = this.mapper.convertEntityToDto(this.service.update(dtoInput));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}