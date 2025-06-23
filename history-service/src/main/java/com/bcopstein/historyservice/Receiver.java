package com.bcopstein.historyservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.bcopstein.historyservice.RegistroDeVenda;
import com.bcopstein.historyservice.RegistroDeVendaJPA_ItfRep;
@Component
public class Receiver {
    public static final String QUEUENAME = "conversions.v1.conversion-request.save-history";
    private static Logger logger = LogManager.getLogger(Receiver.class);

    private final RegistroDeVendaJPA_ItfRep repository;

    public Receiver(RegistroDeVendaJPA_ItfRep repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = QUEUENAME)
    public void receive(RegistroDeVendaDTO dto) {
        logger.info("Mensagem recebida: {}", dto);

        RegistroDeVenda registro = new RegistroDeVenda(
            dto.getData(),
            dto.getValorVenda(),
            dto.getValorImposto()
        );

        repository.save(registro);
    }
}
