package com.bcopstein.sistvendas.aplicacao.strategies.imposto.federal;

import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;

@Service
public interface IImpostoFederalStrategy {
    public double calculaImpostoFederal(PedidoModel pedido);
}
