package com.bcopstein.sistvendas.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "vendas-registro";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }
}
