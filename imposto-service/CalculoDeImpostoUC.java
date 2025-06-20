package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.aplicacao.factories.ImpostoFederalFactory;
import com.bcopstein.sistvendas.aplicacao.factories.ImpostoRegionalFactory;
import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;

@Service
public class CalculoDeImpostoUC {
    private ImpostoFederalFactory impostoFederalFactory;
    private ImpostoRegionalFactory impostoRegionalFactory;

    @Autowired
    public CalculoDeImpostoUC(ImpostoFederalFactory impostoFederalFactory, ImpostoRegionalFactory impostoRegionalFactory) {
        this.impostoFederalFactory = impostoFederalFactory;
        this.impostoRegionalFactory = impostoRegionalFactory;
    }

    public double calculaImpostoFederal(PedidoModel pedido) {
        var impostoFederal = impostoFederalFactory.get(pedido.getPais());
        return impostoFederal.calculaImpostoFederal(pedido);
    }

    public double calculaImpostoRegional(PedidoModel pedido) {
        var impostoRegional = impostoRegionalFactory.get(pedido.getPais(), pedido.getRegiao());
        return impostoRegional.calculaImpostoRegional(pedido);
    }
}
