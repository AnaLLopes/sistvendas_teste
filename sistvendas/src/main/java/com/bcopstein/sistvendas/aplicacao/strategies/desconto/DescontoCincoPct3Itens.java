package com.bcopstein.sistvendas.aplicacao.strategies.desconto;

import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;
import com.bcopstein.sistvendas.dominio.tipos.TipoDesconto;

@Service(TipoDesconto.CincoPctTresItens)
public class DescontoCincoPct3Itens implements IDescontoStrategy {

    @Override
    public double calculaDesconto(PedidoModel pedido) {
        double desconto = 0;

        desconto = pedido.getItens().stream()
                .filter(it -> it.getQuantidade() > 3)
                .mapToDouble(it -> it.getProduto().getPrecoUnitario() * it.getQuantidade())
                .sum();

        return desconto * 0.05;
    }

}
