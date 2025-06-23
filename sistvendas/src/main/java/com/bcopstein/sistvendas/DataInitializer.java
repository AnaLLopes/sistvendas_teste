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
        
        Produto produto1 = new Produto(1L, "Geladeira", 1500.00);
        Produto produto2 = new Produto(2L, "Fogão de Indução", 500.00);
        Produto produto3 = new Produto(3L, "Microondas", 550.00);
        Produto produto4 = new Produto(4L, "Cafeteira", 120.50);
        
        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
        produtoRepository.save(produto3);
        produtoRepository.save(produto4);
        
        
        ItemDeEstoque estoque1 = new ItemDeEstoque(1L, produto1, 200, 50, 500);
        ItemDeEstoque estoque2 = new ItemDeEstoque(2L, produto2, 150, 30, 300);
        ItemDeEstoque estoque3 = new ItemDeEstoque(3L, produto3, 60, 10, 100);
        ItemDeEstoque estoque4 = new ItemDeEstoque(4L, produto4, 180, 40, 400);
        
        estoqueRepository.save(estoque1);
        estoqueRepository.save(estoque2);
        estoqueRepository.save(estoque3);
        estoqueRepository.save(estoque4);
        
        List<ItemPedido> itensPedido1 = new ArrayList<>();
        itensPedido1.add(new ItemPedido(1L, produto1, 10));
        itensPedido1.add(new ItemPedido(2L, produto3, 5));
        
        List<ItemPedido> itensPedido2 = new ArrayList<>();
        itensPedido2.add(new ItemPedido(3L, produto2, 20));
        itensPedido2.add(new ItemPedido(4L, produto4, 15));

         List<ItemPedido> itensPedido3 = new ArrayList<>();
        itensPedido3.add(new ItemPedido(5L, produto3, 1));
        itensPedido3.add(new ItemPedido(6L, produto1, 1));
        
        Orcamento orcamento1 = new Orcamento(1L, itensPedido1, false);
        orcamento1.setPais("Brasil");
        orcamento1.setRegiao("Sudeste");
        orcamento1.setNomeCliente("Carlos");
        orcamento1.setDesconto(5.00);
        orcamento1.setImpostoFederal(3.50);
        orcamento1.setImpostoRegional(1.75);
        
        Orcamento orcamento2 = new Orcamento(2L, itensPedido2, true);
        orcamento2.setPais("Brasil");
        orcamento2.setRegiao("Sul");
        orcamento2.setNomeCliente("Antônio");
        orcamento2.setDesconto(0.00);
        orcamento2.setImpostoFederal(2.00);
        orcamento2.setImpostoRegional(1.00);
        
        Orcamento orcamento3 = new Orcamento(2L, itensPedido3, true);
        orcamento2.setPais("Brasil");
        orcamento2.setRegiao("Pernanbuco");
        orcamento2.setNomeCliente("Bernardo");
        orcamento2.setDesconto(10.00);
        orcamento2.setImpostoFederal(2.00);
        orcamento2.setImpostoRegional(12.00);

        orcamentoRepository.save(orcamento1);
        orcamentoRepository.save(orcamento2);
        orcamentoRepository.save(orcamento3);

    }
}