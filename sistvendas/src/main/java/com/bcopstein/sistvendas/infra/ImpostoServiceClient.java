package com.bcopstein.sistvendas.infra;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;

@Component
public class ImpostoServiceClient {
    private final RestTemplate restTemplate;

    public ImpostoServiceClient() {
        this.restTemplate = new RestTemplate();
    }

    public double calculaImpostoFederal(PedidoModel pedido) {
        return restTemplate.postForObject("http://imposto-service:8082/impostos/federal", pedido, Double.class);
    }

    public double calculaImpostoRegional(PedidoModel pedido) {
        return restTemplate.postForObject("http://imposto-service:8082/impostos/regional", pedido, Double.class);
    }
}
