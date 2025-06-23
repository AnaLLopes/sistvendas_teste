package com.bcopstein.sistvendas.infra;

import com.bcopstein.sistvendas.aplicacao.dtos.RegistroDeVendaDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class VendaPublisher {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "conversions.v1.conversion-request"; 
    public VendaPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarVenda(RegistroDeVendaDTO registro) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", registro);
    }
}