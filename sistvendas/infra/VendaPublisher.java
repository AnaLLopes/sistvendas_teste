package com.bcopstein.sistvendas.infra;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bcopstein.sistvendas.aplicacao.dtos.RegistroDeVendaDTO;

import static com.bcopstein.sistvendas.config.RabbitMQConfig.QUEUE_NAME;

@Component
public class VendaPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarVenda(RegistroDeVendaDTO registro) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, registro);
    }
}
