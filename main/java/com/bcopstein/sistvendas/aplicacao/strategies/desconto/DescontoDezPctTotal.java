package com.bcopstein.sistvendas.aplicacao.strategies.desconto;

import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;
import com.bcopstein.sistvendas.dominio.tipos.TipoDesconto;

@Service(TipoDesconto.DezPctMaisDezItens)
public class DescontoDezPctTotal implements IDescontoStrategy {

    @Override
    public double calculaDesconto(PedidoModel pedido) {
        double desconto = 0;
        int quantItens = 0;

        quantItens = pedido.getItens().stream()
                .mapToInt(it -> it.getQuantidade())
                .sum();

        if (quantItens > 10) {
            double custoItens = pedido.getItens().stream()
                    .mapToDouble(it -> it.getProduto().getPrecoUnitario() * it.getQuantidade())
                    .sum();

            desconto = custoItens * 0.1;
        }

        return desconto;
    }

}
