package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.aplicacao.factories.DescontoFactory;
import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;
import com.bcopstein.sistvendas.dominio.tipos.TipoDesconto;

@Service
public class CalculoDeDescontoUC {
    DescontoFactory descontoFactory;
    
    @Autowired
    public CalculoDeDescontoUC(DescontoFactory descontoFactory) {
        this.descontoFactory = descontoFactory;
    }

    public double calculaDesconto(PedidoModel pedido) {
        var descontoItem = calculaDescontoItem(pedido);
        var descontoTotal = calculaDescontoTotal(pedido);
        return descontoItem + descontoTotal;
    }

    private double calculaDescontoItem(PedidoModel pedido) {
        var desconto = descontoFactory.get(TipoDesconto.CincoPctTresItens);
        return desconto.calculaDesconto(pedido);
    }

    private double calculaDescontoTotal(PedidoModel pedido) {
        var desconto = descontoFactory.get(TipoDesconto.DezPctMaisDezItens);
        return desconto.calculaDesconto(pedido);
    }
}
