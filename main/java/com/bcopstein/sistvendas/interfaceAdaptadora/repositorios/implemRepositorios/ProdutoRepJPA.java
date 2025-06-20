package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.implemRepositorios;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.bcopstein.sistvendas.dominio.modelos.ProdutoModel;
import com.bcopstein.sistvendas.dominio.persistencia.IProdutoRepositorio;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.Produto;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA.ProdutoJPA_ItfRep;

@Repository
@Primary
public class ProdutoRepJPA implements IProdutoRepositorio {
    final ProdutoJPA_ItfRep produtoRepository;

    @Autowired
    public ProdutoRepJPA(ProdutoJPA_ItfRep produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoModel> todos() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            return new LinkedList<>();
        } else {
            return produtos.stream()
                    .map(prod -> prod.toProdutoModel())
                    .toList();
        }
    }

    @Override
    public ProdutoModel consultaPorId(long id) {
        Produto produto = produtoRepository.findById(id);
        System.out.println("Produto de codigo: " + id + ": " + produto);
        if (produto == null) {
            return null;
        } else {
            return produto.toProdutoModel();
        }
    }
}
