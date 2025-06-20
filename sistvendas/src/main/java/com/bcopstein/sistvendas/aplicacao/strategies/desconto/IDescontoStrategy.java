package com.bcopstein.sistvendas.aplicacao.strategies.desconto;

import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;

@Service
public interface IDescontoStrategy {
    public double calculaDesconto(PedidoModel pedido);
}
