package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades;

import java.io.Serializable;
import com.bcopstein.sistvendas.dominio.modelos.ItemDeEstoqueModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
// @Table(name = "item_de_estoque")
public class ItemDeEstoque implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Produto produto;
    private int quantidade;
    private int estoqueMin;
    private int estoqueMax;

    public ItemDeEstoque(long id, Produto produto, int quantidade, int estoqueMin, int estoqueMax) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.estoqueMin = estoqueMin;
        this.estoqueMax = estoqueMax;
    }

    protected ItemDeEstoque() {
    }

    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getEstoqueMin() {
        return estoqueMin;
    }

    public int getEstoqueMax() {
        return estoqueMax;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public void setEstoqueMax(int estoqueMax) {
        this.estoqueMax = estoqueMax;
    }

    public ItemDeEstoqueModel toItemDeEstoqueModel(ItemDeEstoque itemDeEstoque) {
        Produto produto = itemDeEstoque.getProduto();
        return new ItemDeEstoqueModel(
                itemDeEstoque.getId(),
                produto.toProdutoModel(),
                itemDeEstoque.getQuantidade(),
                itemDeEstoque.getEstoqueMin(),
                itemDeEstoque.getEstoqueMax());
    }

    @Override
    public String toString() {
        return "ItemDeEstoque [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", estoqueMin="
                + estoqueMin + ", estoqueMax=" + estoqueMax + "]";
    }
}