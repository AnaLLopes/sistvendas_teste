package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.aplicacao.dtos.ItemPedidoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.PedidoDTO;
import com.bcopstein.sistvendas.dominio.modelos.ItemPedidoModel;
import com.bcopstein.sistvendas.dominio.modelos.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.modelos.PedidoModel;
import com.bcopstein.sistvendas.dominio.modelos.ProdutoModel;
import com.bcopstein.sistvendas.infra.ImpostoServiceClient;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeEstoque;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeVendas;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.CalculoDeDescontoUC;

@Component
public class CriaOrcamentoUC {
    private ServicoDeVendas servicoDeVendas;
    private ServicoDeEstoque servicoDeEstoque;
    private ImpostoServiceClient impostoClient;
    private CalculoDeDescontoUC calculoDeDesconto;

    @Autowired
    public CriaOrcamentoUC(
            ServicoDeVendas servicoDeVendas,
            ServicoDeEstoque servicoDeEstoque,
            ImpostoServiceClient impostoClient,
            CalculoDeDescontoUC calculoDeDesconto) {
        this.servicoDeVendas = servicoDeVendas;
        this.servicoDeEstoque = servicoDeEstoque;
        this.impostoClient = impostoClient;
        this.calculoDeDesconto = calculoDeDesconto;
    }

    public OrcamentoDTO run(PedidoDTO pedidoDTO) {
        List<ItemPedidoDTO> itensDTO = pedidoDTO.getItens();
        List<ItemPedidoModel> itens = new ArrayList<>();
        for (ItemPedidoDTO item : itensDTO) {
            ProdutoModel produto = servicoDeEstoque.produtoPorCodigo(item.getIdProduto());
            ItemPedidoModel itemPedido = new ItemPedidoModel(produto, item.getQtdade());
            itens.add(itemPedido);
        }
        PedidoModel pedido = new PedidoModel(0, itens, pedidoDTO.getPais(), pedidoDTO.getRegiao(),
                pedidoDTO.getNomeCliente());

        var impostoFederal = impostoClient.calculaImpostoFederal(pedido);
        var impostoRegional = impostoClient.calculaImpostoRegional(pedido);
        var desconto = calculoDeDesconto.calculaDesconto(pedido);

        OrcamentoModel orcamento = new OrcamentoModel(0, itens, impostoFederal, impostoRegional, desconto,
                pedido.getPais(), pedido.getRegiao(), pedido.getNomeCliente(), false);

        orcamento = servicoDeVendas.criaOrcamento(orcamento);
        return OrcamentoDTO.fromModel(orcamento);
    }
}
