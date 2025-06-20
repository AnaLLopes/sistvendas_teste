package com.bcopstein.sistvendas.dominio.servicos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.aplicacao.casosDeUso.CalculoDeImpostoUC;
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

    @Autowired
    public ServicoDeVendas(
            IOrcamentoRepositorio orcamentos,
            IEstoqueRepositorio estoque,
            CalculoDeImpostoUC imposto,
            CalculoDeDescontoUC desconto) {
        this.orcamentos = orcamentos;
        this.estoque = estoque;
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
        // Recupera o orçamento
        OrcamentoModel orcamento = this.orcamentos.recuperaPorId(id);
        if (orcamento == null) {
            throw new IllegalArgumentException("Orçamento não encontrado");
        }

        LocalDate validade = LocalDate.now().minusDays(21);
        if (orcamento.getData().isBefore(validade)) {
            throw new IllegalArgumentException("Orçamento antigo");
        }
        
        // Verifica se já está efetivado
        if (orcamento.isEfetivado()) {
            return orcamento;
        }
        
        // Verifica se tem quantidade em estoque para todos os itens
        boolean temEstoqueSuficiente = true;
        for (ItemPedidoModel item : orcamento.getItens()) {
            int qtdEmEstoque = estoque.quantidadeEmEstoque(item.getProduto().getId());
            if (qtdEmEstoque < item.getQuantidade()) {
                temEstoqueSuficiente = false;
                break;
            }
        }
        
        // Se tem quantidade para todos os itens, da baixa no estoque para todos
        if (temEstoqueSuficiente) {
            for (ItemPedidoModel item : orcamento.getItens()) {
                estoque.baixaEstoque(item.getProduto().getId(), item.getQuantidade());
            }
            
            // Marca o orcamento como efetivado
            orcamentos.marcaComoEfetivado(id);
            orcamento = orcamentos.recuperaPorId(id);
        }
        
        return orcamento;
    }
}
