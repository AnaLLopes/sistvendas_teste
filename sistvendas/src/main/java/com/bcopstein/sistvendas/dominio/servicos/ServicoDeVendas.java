package com.bcopstein.sistvendas.dominio.servicos;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.infra.ImpostoServiceClient;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.CalculoDeDescontoUC;
import com.bcopstein.sistvendas.dominio.modelos.ItemPedidoModel;
import com.bcopstein.sistvendas.dominio.modelos.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.modelos.ProdutoModel;
import com.bcopstein.sistvendas.dominio.persistencia.IEstoqueRepositorio;
import com.bcopstein.sistvendas.dominio.persistencia.IOrcamentoRepositorio;

@Service
public class ServicoDeVendas {
    private IOrcamentoRepositorio orcamentos;
    private IEstoqueRepositorio estoque;
    private ImpostoServiceClient impostoClient;
    private CalculoDeDescontoUC desconto;

    @Autowired
    public ServicoDeVendas(
            IOrcamentoRepositorio orcamentos,
            IEstoqueRepositorio estoque,
            ImpostoServiceClient impostoClient,
            CalculoDeDescontoUC desconto) {
        this.orcamentos = orcamentos;
        this.estoque = estoque;
        this.impostoClient = impostoClient;
        this.desconto = desconto;
    }

    public List<ProdutoModel> produtosDisponiveis() {
        return estoque.todosComEstoque();
    }

    public OrcamentoModel recuperaOrcamentoPorId(long id) {
        return this.orcamentos.recuperaPorId(id);
    }

    public OrcamentoModel criaOrcamento(OrcamentoModel orcamento) {
        return this.orcamentos.cadastra(orcamento);
    }

    public OrcamentoModel efetivaOrcamento(long id) {
        OrcamentoModel orcamento = this.orcamentos.recuperaPorId(id);
        if (orcamento == null) {
            throw new IllegalArgumentException("Orçamento não encontrado");
        }

        LocalDate validade = LocalDate.now().minusDays(21);
        if (orcamento.getData().isBefore(validade)) {
            throw new IllegalArgumentException("Orçamento antigo");
        }
        
        if (orcamento.isEfetivado()) {
            return orcamento;
        }
        
        boolean temEstoqueSuficiente = true;
        for (ItemPedidoModel item : orcamento.getItens()) {
            int qtdEmEstoque = estoque.quantidadeEmEstoque(item.getProduto().getId());
            if (qtdEmEstoque < item.getQuantidade()) {
                temEstoqueSuficiente = false;
                break;
            }
        }
        
        if (temEstoqueSuficiente) {
            for (ItemPedidoModel item : orcamento.getItens()) {
                estoque.baixaEstoque(item.getProduto().getId(), item.getQuantidade());
            }
            orcamentos.marcaComoEfetivado(id);
            orcamento = orcamentos.recuperaPorId(id);
        }
        
        return orcamento;
    }
}