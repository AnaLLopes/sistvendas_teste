package com.bcopstein.sistvendas.aplicacao.strategies.imposto.regional;

import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;
import com.bcopstein.sistvendas.dominio.tipos.CodigoRegional;

@Service(CodigoRegional.BR_PE)
public class ImpostoRegionalBR_PE implements IImpostoRegionalStrategy {
    public double calculaImpostoRegional(PedidoModel pedido) {
        double custoItensNaoEssenciais = pedido.getItens().stream()
            .filter(it->!it.getProduto().getDescricao().endsWith("*"))
            .mapToDouble(it->it.getProduto().getPrecoUnitario()*it.getQuantidade())
            .sum();

        return custoItensNaoEssenciais * 0.15;
    };
}
