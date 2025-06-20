package com.bcopstein.sistvendas.aplicacao.strategies.imposto.regional;

import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;
import com.bcopstein.sistvendas.dominio.tipos.CodigoRegional;

@Service(CodigoRegional.BR_SP)
public class ImpostoRegionalBR_SP implements IImpostoRegionalStrategy {
    public double calculaImpostoRegional(PedidoModel pedido) {
        double custoItens = pedido.getItens().stream()
            .mapToDouble(it->it.getProduto().getPrecoUnitario()*it.getQuantidade())
            .sum();

        return custoItens * 0.12;
    };
}
