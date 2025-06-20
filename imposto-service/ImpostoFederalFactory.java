package com.bcopstein.sistvendas.aplicacao.factories;

import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.aplicacao.strategies.imposto.federal.IImpostoFederalStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ImpostoFederalFactory {

    private final Map<String, IImpostoFederalStrategy> impostoFederal;

    public IImpostoFederalStrategy get(String codigoFederal) {
        IImpostoFederalStrategy instance = impostoFederal.get(codigoFederal);

        if (Objects.isNull(instance)) {
            throw new IllegalArgumentException("Pais não suportado");
        }

        return instance;
    }
}
