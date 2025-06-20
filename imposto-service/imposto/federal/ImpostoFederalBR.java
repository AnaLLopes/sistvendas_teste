package com.bcopstein.sistvendas.aplicacao.strategies.imposto.federal;


import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;
import com.bcopstein.sistvendas.dominio.tipos.CodigoFederal;

@Service(CodigoFederal.BR)
public class ImpostoFederalBR implements IImpostoFederalStrategy {
    @Override
    public double calculaImpostoFederal(PedidoModel pedido) {
        double custoItens = pedido.getItens().stream()
            .mapToDouble(it->it.getProduto().getPrecoUnitario()*it.getQuantidade())
            .sum();

        return custoItens * 0.15;
    };
}
