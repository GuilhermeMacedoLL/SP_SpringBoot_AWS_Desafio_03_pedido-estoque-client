package com.pedidosms.pedidos.cloudservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estoquems.estoque.entity.Estoque;
import com.pedidosms.pedidos.entity.Pedidos;
import com.pedidosms.pedidos.enums.SituacaoPedido;
import com.pedidosms.pedidos.repository.PedidosRepository;

@Service
public class PedidosCloudService {

	@Autowired
	private PedidosRepository pedidosRepository;
	
    @Value("${estoque.host}")
    private String estoqueHost;

    @Autowired
    private RestTemplate restTemplate;

    public String realizarPedido(Long clienteId, Long produtoId, int quantidade) {
        String uriVariables = estoqueHost + "/estoque/consulta/{id}";
        
        Estoque estoque = restTemplate.getForObject(uriVariables, Estoque.class, produtoId);
        
        if (estoque == null || Integer.parseInt(estoque.getQuantidade()) < quantidade) {
            return "Estoque insuficiente para o produto com ID " + produtoId;
        }
        
        Pedidos pedido = new Pedidos();
        pedido.setClienteId(clienteId);
        pedido.setId(produtoId);
        pedido.setSituacaoPedido(SituacaoPedido.APROVADO);
        
        pedidosRepository.save(pedido);
        
        return String.format("Pedido realizado com sucesso! Produto ID: %d, Quantidade solicitada: %d. Quantidade disponível no estoque: %d", produtoId, quantidade, estoque.getQuantidade());
    }
}
