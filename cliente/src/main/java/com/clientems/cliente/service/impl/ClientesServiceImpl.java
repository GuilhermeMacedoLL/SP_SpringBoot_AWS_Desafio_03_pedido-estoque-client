package com.clientems.cliente.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientems.cliente.dto.ClientesDTO;
import com.clientems.cliente.entity.Clientes;
import com.clientems.cliente.exception.AplicacaoException;
import com.clientems.cliente.mapper.ClientesMapper;
import com.clientems.cliente.repository.ClientesRepository;
import com.clientems.cliente.service.ClientesService;

@Service
public class ClientesServiceImpl implements ClientesService {
	@Autowired
	private ClientesRepository repository;

	@Autowired
	private ClientesMapper mapper;

	@Override
	public List<Clientes> getAll() {
		List<Clientes> listaDados = repository.findAll();

		if (listaDados == null) {
			return new ArrayList<>();
		}
		return listaDados;
	}

	@Override
	public Clientes getClientes(Long id) {
		Optional<Clientes> dado = repository.findById(id);

		if (!dado.isPresent()) {
			return new Clientes();
		}
		return dado.get();
	}

	@Override
	public Clientes create(ClientesDTO dto) {
	        Clientes dadoExistente = repository.getClientes(dto.getNome());

	        if (dadoExistente != null) {
	            throw new AplicacaoException("Cliente já existe.");
	        }

	        repository.insertCliente(dto.getId(), dto.getCpf(), dto.getNome(), dto.getSobreNome());

	        return repository.findById(dto.getId()).orElseThrow(() -> new AplicacaoException("Erro ao inserir cliente"));
	    }

	@Override
	public Clientes update(ClientesDTO dto) {
		Clientes dadoAtual = repository.getClientes(dto.getNome());
		
		if (dadoAtual == null) {
			return new Clientes();
		}
		
		Clientes dadoNovo = mapper.convertDtoToEntity(dto);
		
		dadoNovo = this.validaCamposUpdate(dadoAtual, dadoNovo);
		
		return repository.save(dadoNovo);
	}
	
	public Clientes validaCamposUpdate(Clientes dadoAtual, Clientes dadoNovo) {
		
		dadoAtual.setNome((dadoAtual.getNome().equals(dadoNovo.getNome()) ? dadoAtual.getNome() : dadoNovo.getNome()));
		dadoAtual.setSobreNome((dadoAtual.getSobreNome().equals(dadoNovo.getSobreNome()) ? dadoAtual.getSobreNome() : dadoNovo.getSobreNome()));
		dadoAtual.setCpf((dadoAtual.getCpf().equals(dadoNovo.getCpf()) ? dadoAtual.getCpf() : dadoNovo.getCpf()));
		
		
		return dadoAtual;
	}

	@Override
	public void delete(Long id) {
		Optional<Clientes> dado = repository.findById(id);
		
		if (!dado.isPresent()) {
			throw new AplicacaoException("Cliente não existe.");
		}
		repository.deleteById(id);
	}
	
	@Override
    public Long getIdByCpf(String cpf) {
        Clientes cliente = repository.getClientesByCpf(cpf);
        if (cliente != null) {
            return cliente.getId();
        }
        return null;
    }

}
