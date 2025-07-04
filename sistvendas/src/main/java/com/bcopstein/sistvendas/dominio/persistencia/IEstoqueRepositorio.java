package com.bcopstein.sistvendas.dominio.persistencia;

import java.util.List;

import com.bcopstein.sistvendas.dominio.modelos.ProdutoModel;

public interface IEstoqueRepositorio {
    List<ProdutoModel> todos();

    List<ProdutoModel> todosComEstoque();

    List<ProdutoModel> todosComEstoquePorLista(List<Long> ids);

    int quantidadeEmEstoque(long codigo);

    int baixaEstoque(long codProd, int qtdade);
}
