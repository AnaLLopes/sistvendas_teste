package com.bcopstein.sistvendas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.ItemDeEstoque;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.ItemPedido;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.Orcamento;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.Pedido;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.Produto;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA.EstoqueJPA_ItfRep;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA.OrcamentoJPA_ItfRep;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA.ProdutoJPA_ItfRep;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProdutoJPA_ItfRep produtoRepository;
    private final EstoqueJPA_ItfRep estoqueRepository;
    private final OrcamentoJPA_ItfRep orcamentoRepository;

    public DataInitializer(
            ProdutoJPA_ItfRep produtoRepository,
            EstoqueJPA_ItfRep estoqueRepository,
            OrcamentoJPA_ItfRep orcamentoRepository) {
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
        this.orcamentoRepository = orcamentoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create and save products
        Produto produto1 = new Produto(1L, "Caneta Esferográfica Azul", 25.50);
        Produto produto2 = new Produto(2L, "Lápis Preto Nº3", 10.00);
        Produto produto3 = new Produto(3L, "Mochila Escolar", 150.00);
        Produto produto4 = new Produto(4L, "Borracha Branca", 5.75);
        
        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
        produtoRepository.save(produto3);
        produtoRepository.save(produto4);
        
        // Create and save estoque items
        ItemDeEstoque estoque1 = new ItemDeEstoque(1L, produto1, 200, 50, 500);
        ItemDeEstoque estoque2 = new ItemDeEstoque(2L, produto2, 150, 30, 300);
        ItemDeEstoque estoque3 = new ItemDeEstoque(3L, produto3, 60, 10, 100);
        ItemDeEstoque estoque4 = new ItemDeEstoque(4L, produto4, 180, 40, 400);
        
        estoqueRepository.save(estoque1);
        estoqueRepository.save(estoque2);
        estoqueRepository.save(estoque3);
        estoqueRepository.save(estoque4);
        
        // Create and save Pedidos (not directly saved but through Orcamento)
        List<ItemPedido> itensPedido1 = new ArrayList<>();
        itensPedido1.add(new ItemPedido(1L, produto1, 10));
        itensPedido1.add(new ItemPedido(2L, produto3, 5));
        
        List<ItemPedido> itensPedido2 = new ArrayList<>();
        itensPedido2.add(new ItemPedido(3L, produto2, 20));
        itensPedido2.add(new ItemPedido(4L, produto4, 15));
        
        // Create and save Orcamentos
        Orcamento orcamento1 = new Orcamento(1L, itensPedido1, false);
        orcamento1.setPais("Brasil");
        orcamento1.setRegiao("Sudeste");
        orcamento1.setNomeCliente("Maria Souza");
        orcamento1.setDesconto(5.00);
        orcamento1.setImpostoFederal(3.50);
        orcamento1.setImpostoRegional(1.75);
        
        Orcamento orcamento2 = new Orcamento(2L, itensPedido2, true);
        orcamento2.setPais("Brasil");
        orcamento2.setRegiao("Sul");
        orcamento2.setNomeCliente("João Silva");
        orcamento2.setDesconto(0.00);
        orcamento2.setImpostoFederal(2.00);
        orcamento2.setImpostoRegional(1.00);
        
        orcamentoRepository.save(orcamento1);
        orcamentoRepository.save(orcamento2);
        
        System.out.println("Sample data has been loaded!");
    }
}