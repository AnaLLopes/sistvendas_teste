package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.implemRepositorios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.bcopstein.sistvendas.dominio.modelos.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.persistencia.IOrcamentoRepositorio;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.ItemPedido;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.Orcamento;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA.OrcamentoJPA_ItfRep;

@Repository
@Primary
public class OrcamentoRepJPA implements IOrcamentoRepositorio {
    final OrcamentoJPA_ItfRep orcamentoRepository;

    @Autowired
    public OrcamentoRepJPA(OrcamentoJPA_ItfRep orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    @Override
    public List<OrcamentoModel> todos() {
        List<Orcamento> itens = orcamentoRepository.findAll();
        return itens.stream()
                .map(it -> it.toOrcamentoModel())
                .toList();
    }

    @Override
    public OrcamentoModel cadastra(OrcamentoModel orcamento) {
        List<ItemPedido> itemPedidoModel = orcamento.getItens().stream()
                .map(item -> ItemPedido.fromItemPedidoModel(item)).toList();
        Orcamento orcamentoEntidade = new Orcamento(orcamento.getId(), itemPedidoModel, orcamento.isEfetivado());
        
        // Copy all additional fields from the model to the entity
        orcamentoEntidade.setPais(orcamento.getPais());
        orcamentoEntidade.setRegiao(orcamento.getRegiao());
        orcamentoEntidade.setNomeCliente(orcamento.getNomeCliente());
        orcamentoEntidade.setImpostoFederal(orcamento.getImpostoFederal());
        orcamentoEntidade.setImpostoRegional(orcamento.getImpostoRegional());
        orcamentoEntidade.setDesconto(orcamento.getDesconto());
        
        orcamentoRepository.save(orcamentoEntidade);
        return orcamentoEntidade.toOrcamentoModel();
    }

    @Override
    public OrcamentoModel recuperaPorId(long id) {
        Orcamento orcamento = orcamentoRepository.findById(id).orElse(null);
        if (orcamento == null) {
            return null;
        }
        return orcamento.toOrcamentoModel();
    }

    @Override
    public void marcaComoEfetivado(long id) {
        Orcamento orcamento = orcamentoRepository.findById(id).orElse(null);
        if (orcamento == null) {
            throw new IllegalArgumentException("Orcamento inexistente");
        }
        orcamento.efetiva();
        orcamentoRepository.save(orcamento);
    }
}
