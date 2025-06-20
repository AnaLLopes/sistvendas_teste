package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoDTO;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeEstoque;

@Component
public class ProdutosDisponiveisPorListaUC {
    private ServicoDeEstoque servicoEstoque;

    @Autowired
    public ProdutosDisponiveisPorListaUC(ServicoDeEstoque servicoEstoque) {
        this.servicoEstoque = servicoEstoque;
    }

    public List<ProdutoDTO> run(List<Long> ids) {
        return servicoEstoque.produtosDisponiveisPorLista(ids).stream()
            .map(p -> ProdutoDTO.fromModel(p))
            .toList();
    }
}