package com.estoquems.estoque.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoquems.estoque.dto.EstoqueDTO;
import com.estoquems.estoque.entity.Estoque;
import com.estoquems.estoque.exception.AplicacaoException;
import com.estoquems.estoque.mapper.EstoqueMapper;
import com.estoquems.estoque.repository.EstoqueRepository;
import com.estoquems.estoque.service.EstoqueService;

@Service
public class EstoqueServiceImpl implements EstoqueService {
	@Autowired
	private EstoqueRepository repository;

	@Autowired
	private EstoqueMapper mapper;

	@Override
	public List<Estoque> getAll() {
		List<Estoque> listaDados = repository.findAll();

		if (listaDados == null) {
			return new ArrayList<>();
		}
		return listaDados;
	}

	@Override
	public Estoque getEstoque(Long id) {
		Optional<Estoque> dado = repository.findById(id);

		if (!dado.isPresent()) {
			return new Estoque();
		}
		return dado.get();
	}

	@Override
	public Estoque create(EstoqueDTO dto) {
		Estoque dado = repository.getEstoque(dto.getNomeProduto());

		if (dado != null) {
			return new Estoque();
		}

		dado = mapper.convertDtoToEntity(dto);

		return repository.save(dado);
	}

	@Override
	public Estoque update(EstoqueDTO dto) {
		Estoque dadoAtual = repository.getEstoque(dto.getNomeProduto());

		if (dadoAtual == null) {
			return new Estoque();
		}

		Estoque dadoNovo = mapper.convertDtoToEntity(dto);

		dadoNovo = this.validaCamposUpdate(dadoAtual, dadoNovo);

		return repository.save(dadoNovo);
	}

	public Estoque validaCamposUpdate(Estoque dadoAtual, Estoque dadoNovo) {

		dadoAtual.setQuantidade((dadoAtual.getQuantidade().equals(dadoNovo.getQuantidade()) ? dadoAtual.getQuantidade()
				: dadoNovo.getQuantidade()));
		dadoAtual.setNomeProduto(
				(dadoAtual.getNomeProduto().equals(dadoNovo.getNomeProduto()) ? dadoAtual.getNomeProduto()
						: dadoNovo.getNomeProduto()));

		return dadoAtual;
	}

	@Override
	public void delete(Long id) {
		Optional<Estoque> dado = repository.findById(id);

		if (!dado.isPresent()) {
			throw new AplicacaoException("Produto não existe.");
		}
		repository.deleteById(id);
	}

}