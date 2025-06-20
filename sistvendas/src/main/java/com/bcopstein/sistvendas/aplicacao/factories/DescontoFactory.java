package com.bcopstein.sistvendas.aplicacao.factories;

import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.aplicacao.strategies.desconto.IDescontoStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DescontoFactory {
    
    private final Map<String, IDescontoStrategy> desconto;

    public IDescontoStrategy get(String tipoDesconto) {
        IDescontoStrategy instance = desconto.get(tipoDesconto);

        if (Objects.isNull(instance)) {
            throw new IllegalArgumentException("Desconto não suportado");
        }

        return instance;
    }
}
