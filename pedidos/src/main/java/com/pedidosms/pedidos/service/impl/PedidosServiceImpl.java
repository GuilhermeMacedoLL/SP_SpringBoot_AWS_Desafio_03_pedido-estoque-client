package com.pedidosms.pedidos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedidosms.pedidos.dto.PedidosDTO;
import com.pedidosms.pedidos.entity.Pedidos;
import com.pedidosms.pedidos.exception.AplicacaoException;
import com.pedidosms.pedidos.mapper.PedidosMapper;
import com.pedidosms.pedidos.repository.PedidosRepository;
import com.pedidosms.pedidos.service.PedidosService;

@Service
public class PedidosServiceImpl implements PedidosService {

	@Autowired
	private PedidosRepository repository;

	@Autowired
	private PedidosMapper mapper;

	@Override
	public List<Pedidos> getAll() {
		List<Pedidos> listaDados = repository.findAll();

		if (listaDados == null) {
			return new ArrayList<>();
		}
		return listaDados;
	}

	@Override
	public Pedidos getPedidos(Long id) {
		Optional<Pedidos> dado = repository.findById(id);

		if (!dado.isPresent()) {
			return new Pedidos();
		}
		return dado.get();
	}

	@Override
	public Pedidos create(PedidosDTO dto) {
		Pedidos dado = repository.getPedidos(dto.getId());

		if (dado != null) {
			return new Pedidos();
		}

		dado = mapper.convertDtoToEntity(dto);

		return repository.save(dado);
	}

	@Override
	public Pedidos update(PedidosDTO dto) {
		Pedidos dadoAtual = repository.getPedidos(dto.getId());

		if (dadoAtual == null) {
			return new Pedidos();
		}

		Pedidos dadoNovo = mapper.convertDtoToEntity(dto);

		dadoNovo = this.validaCamposUpdate(dadoAtual, dadoNovo);

		return repository.save(dadoNovo);
	}

	public Pedidos validaCamposUpdate(Pedidos dadoAtual, Pedidos dadoNovo) {

		dadoAtual.setSituacaoPedido(
				(dadoAtual.getSituacaoPedido().equals(dadoNovo.getSituacaoPedido()) ? dadoAtual.getSituacaoPedido()
						: dadoNovo.getSituacaoPedido()));
		dadoAtual.setClienteId((dadoAtual.getClienteId().equals(dadoNovo.getClienteId()) ? dadoAtual.getClienteId()
				: dadoNovo.getClienteId()));

		return dadoAtual;
	}

	@Override
	public void delete(Long id) {
		Optional<Pedidos> dado = repository.findById(id);

		if (!dado.isPresent()) {
			throw new AplicacaoException("Pedido não existe.");
		}
		repository.deleteById(id);
	}

}
