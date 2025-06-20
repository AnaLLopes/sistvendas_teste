package com.bcopstein.sistvendas.aplicacao.strategies.imposto.regional;

import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;
import com.bcopstein.sistvendas.dominio.tipos.CodigoRegional;

@Service(CodigoRegional.BR_RS)
public class ImpostoRegionalBR_RS implements IImpostoRegionalStrategy {
    public double calculaImpostoRegional(PedidoModel pedido) {
        double custoItens = pedido.getItens().stream()
            .mapToDouble(it->it.getProduto().getPrecoUnitario()*it.getQuantidade())
            .sum();

        double imposto = 0;

        if (custoItens > 100) 
            imposto = (custoItens - 100) * 0.1;
        
        return imposto;
    };
}
