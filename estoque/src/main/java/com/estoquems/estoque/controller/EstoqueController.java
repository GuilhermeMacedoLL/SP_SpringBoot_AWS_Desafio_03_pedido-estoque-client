package com.estoquems.estoque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoquems.estoque.dto.EstoqueDTO;
import com.estoquems.estoque.mapper.EstoqueMapper;
import com.estoquems.estoque.service.EstoqueService;
import com.estoquems.estoque.util.MediaType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/estoque")
public class EstoqueController {

	@Autowired
	private EstoqueService service;

	@Autowired
	private EstoqueMapper mapper;

	@GetMapping(value = "/consulta", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<EstoqueDTO>> getAll() {
		List<EstoqueDTO> listaDto = new ArrayList<>();
		listaDto = this.mapper.convertListEntityToListDto(this.service.getAll());
		return new ResponseEntity<List<EstoqueDTO>>(listaDto, HttpStatus.OK);
	}

	@GetMapping(value = "/consulta/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<EstoqueDTO> getNomeProduto(@PathVariable Long id) {
		EstoqueDTO dto = new EstoqueDTO();
		dto = this.mapper.convertEntityToDto(this.service.getEstoque(id));
		return new ResponseEntity<EstoqueDTO>(dto, HttpStatus.OK);
	}

	@GetMapping(value = "/consulta/produto/{nomeProduto}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<List<EstoqueDTO>> getEstoqueProduto(@PathVariable String nomeProduto) {
		List<EstoqueDTO> listaDto = new ArrayList<>();
		listaDto = this.mapper.convertListEntityToListDto(this.service.getEstoqueProduto(nomeProduto));
		return new ResponseEntity<List<EstoqueDTO>>(listaDto, HttpStatus.OK);
	}
	
	@PostMapping(value = "/salvar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<EstoqueDTO> create(@RequestBody EstoqueDTO dtoInput) {
		EstoqueDTO dto = new EstoqueDTO();
		dto = this.mapper.convertEntityToDto(this.service.create(dtoInput));
		return new ResponseEntity<EstoqueDTO>(dto, HttpStatus.CREATED);
	}

	@PutMapping(value = "/alterar", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<EstoqueDTO> update(@RequestBody EstoqueDTO dtoInput) {
		EstoqueDTO dto = new EstoqueDTO();
		dto = this.mapper.convertEntityToDto(this.service.update(dtoInput));
		return new ResponseEntity<EstoqueDTO>(dto, HttpStatus.NO_CONTENT);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/excluir/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}