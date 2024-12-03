package com.pedidosms.pedidos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedidosms.pedidos.dto.EstoqueDTO;
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

	@Autowired
	private RestTemplate restTemplate;

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
		if (getEstoque(dto.getNomeProduto()) == null) {
			throw new RuntimeException("Produto não disponivel no estoque");
		}
		atualizaEstoque(dto.getNomeProduto(), dto.getQuantidadeProduto());
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

	@Override
	public List<Pedidos> getPedidosByClienteId(Long clienteId) {
		List<Pedidos> pedidos = repository.getPedidosByClienteId(clienteId);

		if (pedidos == null || pedidos.isEmpty()) {
			return new ArrayList<>();
		}
		return pedidos;
	}

	private EstoqueDTO getEstoque(String nomeProduto) {
		EstoqueDTO estoque = null;
		if (nomeProduto != null) {
			String url = "http://localhost:8081/estoque/consulta/produto/nomeProduto";

			try {
				ResponseEntity<String> response = restTemplate.exchange(url.replace("nomeProduto", nomeProduto),
						HttpMethod.GET, null, String.class);
				if (response.getBody() != null) {
					ObjectMapper objectMapper = new ObjectMapper();
					EstoqueDTO[] listEstoque = objectMapper.readValue(response.getBody(), EstoqueDTO[].class);

					for (EstoqueDTO dto : listEstoque) {
						estoque = dto;
						break;
					}
				}

			} catch (Exception e) {
				throw new RuntimeException("Erro ao chamar o serviço de pedidos: ", e);
			}
		}
		return estoque;
	}

	private void atualizaEstoque(String nomeProduto, Integer quantidade) {
		if (nomeProduto != null && quantidade > 0) {
			String url = "http://localhost:8081/estoque/alterar";
			EstoqueDTO dto = new EstoqueDTO(nomeProduto, quantidade);
			try {
				HttpEntity<EstoqueDTO> requestEntity = new HttpEntity<EstoqueDTO>(dto);
				ResponseEntity<String> response = restTemplate.exchange(url.replace("nomeProduto", nomeProduto),
						HttpMethod.PUT, requestEntity, String.class);
				if (response.getBody() == null) {
					throw new RuntimeException("Produto não existe");
				}

			} catch (Exception e) {
				throw new RuntimeException("Erro ao chamar o serviço de pedidos: ", e);
			}
		}
	}

}
