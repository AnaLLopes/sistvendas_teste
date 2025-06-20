package com.bcopstein.sistvendas.aplicacao.strategies.imposto.regional;

import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;

public interface IImpostoRegionalStrategy {
    public double calculaImpostoRegional(PedidoModel pedido);
}
