package com.pedidosms.pedidos.cloudservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pedidosms.pedidos.dto.ClientesDTO;
import com.pedidosms.pedidos.entity.Pedidos;
import com.pedidosms.pedidos.repository.PedidosRepository;

@Service
public class PedidosCloudService {

	@Autowired
	private PedidosRepository pedidosRepository;
	
    @Value("${clientes.host}")
    private String clientesHost;

    @Autowired
    private RestTemplate restTemplate;

    public String buscarPedidoDetalhado(Long id) {
        Pedidos pedido = pedidosRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));


        ClientesDTO cliente = restTemplate.getForObject(clientesHost + "/clientes/{clienteId}", ClientesDTO.class, pedido.getClienteId());

        if (cliente == null) {
            return "Cliente não encontrado";
        }

        return String.format("Pedido ID: %d, Cliente: %s %s, Status: %s",
                pedido.getId(),
                cliente.getNome(),
                cliente.getSobreNome(),
                pedido.getSituacaoPedido().name());
    }
}
