package com.bcopstein.sistvendas.aplicacao.factories;

import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.aplicacao.strategies.imposto.regional.IImpostoRegionalStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ImpostoRegionalFactory {

    private final Map<String, IImpostoRegionalStrategy> impostoRegional;

    public IImpostoRegionalStrategy get(String codigoFederal, String codigoRegional) {
        IImpostoRegionalStrategy instance = impostoRegional.get(codigoFederal + "_" + codigoRegional);

        if (Objects.isNull(instance)) {
            throw new IllegalArgumentException("Região não suportado");
        }

        return instance;
    }
}
