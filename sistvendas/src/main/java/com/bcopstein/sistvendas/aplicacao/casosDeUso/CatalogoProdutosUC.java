package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoDTO;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeEstoque;

@Component
public class CatalogoProdutosUC {
    private ServicoDeEstoque servicoEstoque;

	public CatalogoProdutosUC(ServicoDeEstoque servicoEstoque) {
		this.servicoEstoque = servicoEstoque;
	}

	public List<ProdutoDTO> run(){
        return servicoEstoque.todos().stream()
            .map(p->ProdutoDTO.fromModel(p))
            .toList();
    }
}
